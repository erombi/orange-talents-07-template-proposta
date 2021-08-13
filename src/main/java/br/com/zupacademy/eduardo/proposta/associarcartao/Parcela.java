package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tb_cartao_parcela")
public class Parcela {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private Integer quantidade;
    private BigDecimal valor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public Parcela() {
    }

    public Parcela(ParcelaResponse response) {
        this.uuid = response.getUuid();
        this.quantidade = response.getQuantidade();
        this.valor = response.getValor();
    }

    public String getUuid() {
        return uuid;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Cartao getCartao() {
        return cartao;
    }
}
