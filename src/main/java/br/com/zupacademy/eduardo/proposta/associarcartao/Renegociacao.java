package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Renegociacao {

    private String id;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Renegociacao(String id, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }
}
