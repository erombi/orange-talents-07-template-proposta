package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class Vencimento {

    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Vencimento(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }
}
