package br.com.zupacademy.eduardo.proposta.novaproposta;

import br.com.zupacademy.eduardo.proposta.acompanhaproposta.PropostaResponse;
import br.com.zupacademy.eduardo.proposta.compartilhado.ExecutorTransaction;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.AnaliseFinanceiraClient;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.ResultadoAnalise;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.ResultadoSolicitacao;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.SolicitacaoAnalise;
import feign.FeignException;
import io.opentracing.Tracer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @Autowired
    private Tracer tracer;

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private AnaliseFinanceiraClient client;

    @Autowired
    private ExecutorTransaction executor;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder) {
        tracer.activeSpan().setTag("user.email", "eduardo@zup.com");

        Optional<Proposta> exists = repository.findByDocumento(request.getDocumento());
        if (exists.isPresent()) return ResponseEntity.unprocessableEntity().build();

        Proposta proposta = request.toModel();

        executor.inTransaction(() -> {
            repository.save(proposta);
        });

        try {
            SolicitacaoAnalise solicitacaoAnalise = new SolicitacaoAnalise(proposta);
            ResultadoAnalise resultadoAnalise = client.analisaProposta(solicitacaoAnalise);

            executor.inTransaction(() -> {
                proposta.atualizaStatus(resultadoAnalise.getResultadoSolicitacao());
                repository.save(proposta);
            });

            logger.info("Status Code [201] Created AnaliseFinanceira");
        } catch (FeignException e){
            logger.error("Status Code [422] UnproccessableEntity AnaliseFinanceira");

            executor.inTransaction(() -> {
                proposta.atualizaStatus(ResultadoSolicitacao.COM_RESTRICAO);
            });
        }

        URI uri = builder.path("/propostas/{id}").buildAndExpand(proposta.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @GetMapping("/{idProposta}")
    public ResponseEntity<PropostaResponse> acompanhamento(@PathVariable UUID idProposta) {
        Optional<Proposta> possivelProposta = repository.findById(idProposta);

        if (possivelProposta.isEmpty()) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new PropostaResponse(possivelProposta.get()));
    }

}
