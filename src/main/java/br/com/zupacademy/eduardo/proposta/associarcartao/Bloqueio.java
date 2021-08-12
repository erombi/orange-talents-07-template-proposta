package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class Bloqueio {

    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Bloqueio(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }
}
