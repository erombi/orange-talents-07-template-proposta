package br.com.zupacademy.eduardo.proposta.associarcartao;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_cartao_bloqueio")
public class BloqueioCartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String uuid;
    private LocalDateTime bloqueadoEm = LocalDateTime.now();
    private String sistemaResponsavel;
    private String ipOrigem;
    private String userAgent;
    private boolean ativo;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public BloqueioCartao() {
    }

    public BloqueioCartao(Cartao cartao, String userAgent, String ipOrigem) {
        this.uuid = UUID.randomUUID().toString();
        this.bloqueadoEm = LocalDateTime.now();
        this.sistemaResponsavel = "api-proposta";
        this.ipOrigem = ipOrigem;
        this.userAgent = userAgent;
        this.cartao = cartao;
    }

    public BloqueioCartao(Bloqueio response) {
        this.uuid = response.getId();
        this.bloqueadoEm = response.getBloqueadoEm();
        this.sistemaResponsavel = response.getSistemaResponsavel();
        this.ativo = response.isAtivo();
    }


    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BloqueioCartao bloqueioCartao = (BloqueioCartao) o;
        return Objects.equals(id, bloqueioCartao.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
