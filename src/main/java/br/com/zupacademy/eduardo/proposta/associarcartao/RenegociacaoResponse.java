package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RenegociacaoResponse {

    private String uuid;
    private Integer quantidade;
    private BigDecimal valor;
    private LocalDateTime dataDeCriacao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RenegociacaoResponse(String uuid, Integer quantidade, BigDecimal valor, LocalDateTime dataDeCriacao) {
        this.uuid = uuid;
        this.quantidade = quantidade;
        this.valor = valor;
        this.dataDeCriacao = dataDeCriacao;
    }

    public RenegociacaoResponse(Renegociacao renegociacao) {
        this.uuid = renegociacao.getUuid();
        this.quantidade = renegociacao.getQuantidade();
        this.valor = renegociacao.getValor();
        this.dataDeCriacao = renegociacao.getDataDeCriacao();
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

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
