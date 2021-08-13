package br.com.zupacademy.eduardo.proposta.associarcartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.Instant;
import java.util.Objects;

@Entity
@Table(name = "tb_cartao_biometria")
public class Biometria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Lob
    private String fingerprint;

    @ManyToOne
    private Cartao cartao;

    private Instant criadoEm = Instant.now();

    @Deprecated
    public Biometria() {
    }

    public Biometria(EncodedFinger encodedFinger, Cartao cartao) {
        this.fingerprint = encodedFinger.get();
        this.cartao = cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Biometria biometria = (Biometria) o;
        return Objects.equals(fingerprint, biometria.fingerprint);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fingerprint);
    }

    public Long getId() {
        return id;
    }
}
