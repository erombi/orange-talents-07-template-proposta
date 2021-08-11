package br.com.zupacademy.eduardo.proposta.novaproposta;

import br.com.zupacademy.eduardo.proposta.compartilhado.ExecutorTransaction;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.AnaliseFinanceiraClient;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.ResultadoAnalise;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.ResultadoSolicitacao;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.SolicitacaoAnalise;
import feign.FeignException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Optional;

@RestController
@RequestMapping("/propostas")
public class PropostaController {

    private final Logger logger = LoggerFactory.getLogger(PropostaController.class);

    @Autowired
    private PropostaRepository repository;

    @Autowired
    private AnaliseFinanceiraClient client;

    @Autowired
    private ExecutorTransaction executor;

    @PostMapping
    public ResponseEntity<?> insert(@RequestBody @Valid PropostaRequest request, UriComponentsBuilder builder) {
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

}
