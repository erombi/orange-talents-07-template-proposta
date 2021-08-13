package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.math.BigDecimal;

public class ParcelaResponse {

    private String uuid;
    private Integer quantidade;
    private BigDecimal valor;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public ParcelaResponse(String uuid, Integer quantidade, BigDecimal valor) {
        this.uuid = uuid;
        this.quantidade = quantidade;
        this.valor = valor;
    }

    public ParcelaResponse(Parcela parcela) {
        this.uuid = parcela.getUuid();
        this.quantidade = parcela.getQuantidade();
        this.valor = parcela.getValor();
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
}
