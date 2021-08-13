package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class BloqueioResponse {

    private String id;
    private LocalDateTime bloqueadoEm;
    private String sistemaResponsavel;
    private boolean ativo;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public BloqueioResponse(String id, LocalDateTime bloqueadoEm, String sistemaResponsavel, boolean ativo) {
        this.id = id;
        this.bloqueadoEm = bloqueadoEm;
        this.sistemaResponsavel = sistemaResponsavel;
        this.ativo = ativo;
    }

    public BloqueioResponse(Bloqueio bloqueio) {
        this.id = bloqueio.getUuid();
        this.bloqueadoEm = bloqueio.getBloqueadoEm();
        this.sistemaResponsavel = bloqueio.getSistemaResponsavel();
        this.ativo = bloqueio.isAtivo();
    }

    public String getId() {
        return id;
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
}
