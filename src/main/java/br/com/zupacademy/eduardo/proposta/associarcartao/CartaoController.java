package br.com.zupacademy.eduardo.proposta.associarcartao;

import br.com.zupacademy.eduardo.proposta.compartilhado.ExecutorTransaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/cartoes")
public class CartaoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ExecutorTransaction executor;

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


}
