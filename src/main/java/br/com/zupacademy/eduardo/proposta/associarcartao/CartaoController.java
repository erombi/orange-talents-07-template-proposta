package br.com.zupacademy.eduardo.proposta.associarcartao;

import br.com.zupacademy.eduardo.proposta.compartilhado.ExecutorTransaction;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ExecutorTransaction executor;

    @Autowired
    private ApiCartaoClient client;

    @Value("${spring.application.name:api-proposta}")
    private String appName;

    @PostMapping("/{idCartao}/biometrias")
    public ResponseEntity<?> cadastraBiometria(@PathVariable Long idCartao, @RequestBody @Valid BiometriaRequest request,
                                               UriComponentsBuilder builder) {
        Cartao cartao = manager.find(Cartao.class, idCartao);
        if (cartao == null) return ResponseEntity.notFound().build();

        Biometria biometria = request.toModel(cartao);

        executor.inTransaction(() -> {
            manager.persist(biometria);
        });

        URI uri = builder.path("/cartoes/{idCartao}/biometrias/{idBiometria}").buildAndExpand(cartao.getId(), biometria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PostMapping("/{idCartao}/bloqueios")
    public ResponseEntity<?> bloqueiaCartao(@PathVariable Long idCartao, HttpServletRequest request) {
        Cartao cartao = manager.find(Cartao.class, idCartao);
        if (cartao == null) return ResponseEntity.notFound().build();

        Map<String, String> header = getHeader(request);


        try {
            OrdemDeBloqueioResponse ordemDeBloqueioResponse = client.bloqueiaCartao(cartao.getNumeroCartao(), getBody());
            cartao.atualizaBloqueio(ordemDeBloqueioResponse, header.get("userAgent"), header.get("ip"));

            executor.inTransaction(() -> {
                manager.merge(cartao);
            });

            return ResponseEntity.ok().build();
        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) return ResponseEntity.unprocessableEntity().build();

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{idCartao}/avisos")
    public ResponseEntity<?> cadastroAviso(@PathVariable Long idCartao, HttpServletRequest request,
                                            @RequestBody @Valid AvisoViagemRequest avisoViagemRequestrequest) {
        Cartao cartao = manager.find(Cartao.class, idCartao);
        if (cartao == null) return ResponseEntity.notFound().build();

        try {
            RespostaAvisoViagem avisoViagemResponse = client.cadastraAviso(cartao.getNumeroCartao(), avisoViagemRequestrequest);
            Map<String, String> header = getHeader(request);

            if (avisoViagemResponse.getResultado().equals(ResultadoAviso.FALHA)) return ResponseEntity.unprocessableEntity().build();

            AvisoViagem avisoViagem = new AvisoViagem(avisoViagemRequestrequest, cartao, header.get("userAgent"), header.get("ip"));
            boolean adicionado = cartao.adicionaAviso(avisoViagem);

            if (!adicionado) return ResponseEntity.unprocessableEntity().build();

            executor.inTransaction(() -> {
                manager.merge(cartao);
            });

            return ResponseEntity.ok().build();

        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).build();

            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/{idCartao}/carteiras")
    public ResponseEntity<?> criaCarteira(@PathVariable Long idCartao, @RequestBody @Valid SolicitacaoCarteiraRequest request,
                                            UriComponentsBuilder builder) {
        Cartao cartao = manager.find(Cartao.class, idCartao);
        if (cartao == null) return ResponseEntity.notFound().build();

        try {
            RespostaCarteiraDigital respostaCarteiraDigital = client.cadastraCarteira(cartao.getNumeroCartao(), request);

            if (respostaCarteiraDigital.getResultado().equals(ResultadoCarteira.FALHA)) return ResponseEntity.unprocessableEntity().build();

            CarteiraDigital carteiraDigital = respostaCarteiraDigital.toModel(cartao, request);
            boolean associado = cartao.associaCarteira(carteiraDigital);

            if (!associado) return ResponseEntity.unprocessableEntity().build();

            executor.inTransaction(() -> {
                manager.merge(cartao);
            });

            URI uri = builder.path("/cartoes/{idCartao}/carteiras/{idCarteira}").buildAndExpand(cartao.getId(), carteiraDigital.getId()).toUri();
            return ResponseEntity.created(uri).build();

        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) return ResponseEntity.unprocessableEntity().build();

            return ResponseEntity.badRequest().build();
        }
    }

    private Map<String, String> getBody() {
        Map<String, String> map = new HashMap<>();
        map.put("sistemaResponsavel", appName);
        return map;
    }

    public Map<String, String> getHeader(HttpServletRequest request) {
        HashMap<String, String> header = new HashMap<>();

        header.put("userAgent",request.getHeader("User-Agent"));
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) ipAddress = request.getRemoteAddr();
        header.put("ip", ipAddress);

        return header;
    }


}
