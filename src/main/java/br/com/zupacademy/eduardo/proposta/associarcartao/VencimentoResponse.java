package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.time.LocalDateTime;

public class VencimentoResponse {

    private String id;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public VencimentoResponse(String id, Integer dia, LocalDateTime dataDeCriacao) {
        this.id = id;
        this.dia = dia;
        this.dataDeCriacao = dataDeCriacao;
    }

    public VencimentoResponse(Vencimento vencimento) {
        this.id = vencimento.getUuid();
        this.dia = vencimento.getDia();
        this.dataDeCriacao = vencimento.getDataDeCriacao();
    }

    public String getId() {
        return id;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
