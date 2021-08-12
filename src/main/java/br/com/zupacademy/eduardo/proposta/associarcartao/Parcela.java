package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;

public class Parcela {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Parcela(String id, Integer quantidade, BigDecimal valor) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
    }
}
