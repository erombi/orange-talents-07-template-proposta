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

        String userAgent = request.getHeader("User-Agent");
        String ipAddress = request.getHeader("X-FORWARDED-FOR");
        if (ipAddress == null) {
            ipAddress = request.getRemoteAddr();
        }

        try {
            OrdemDeBloqueioResponse ordemDeBloqueioResponse = client.bloqueiaCartao(cartao.getNumeroCartao(), getBody());
            cartao.atualizaBloqueio(ordemDeBloqueioResponse, userAgent, ipAddress);

            executor.inTransaction(() -> {
                manager.merge(cartao);
            });

            return ResponseEntity.ok().build();
        } catch (FeignException e) {
            if (e.status() == HttpStatus.UNPROCESSABLE_ENTITY.value()) return ResponseEntity.unprocessableEntity().build();

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    private Map<String, String> getBody() {
        Map<String, String> map = new HashMap<>();
        map.put("sistemaResponsavel", appName);
        return map;
    }


}
