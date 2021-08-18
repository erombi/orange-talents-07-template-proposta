package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name = "tb_cartao_aviso_viagem")
public class AvisoViagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate validoAte;
    private String destino;

    private String userAgent;
    private String ipOrigem;

    private Instant criadoEm = Instant.now();

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public AvisoViagem() {
    }

    public AvisoViagem(AvisoViagemResponse response, Cartao cartao) {
        this.validoAte = response.getValidoAte();
        this.destino = response.getDestino();
        this.userAgent = userAgent;
        this.ipOrigem = ipOrigem;
    }

    public AvisoViagem(AvisoViagemRequest request, Cartao cartao, String userAgent, String ipOrigem) {
        this.validoAte = request.getValidoAte();
        this.destino = request.getDestino();
        this.cartao = cartao;
        this.userAgent = userAgent;
        this.ipOrigem = ipOrigem;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AvisoViagem that = (AvisoViagem) o;
        return Objects.equals(validoAte, that.validoAte) && Objects.equals(destino, that.destino) && Objects.equals(cartao, that.cartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(validoAte, destino, cartao);
    }
}
