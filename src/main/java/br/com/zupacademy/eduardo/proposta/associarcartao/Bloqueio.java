package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class Bloqueio {

    @JsonProperty
    private String id;

    @JsonProperty
    private LocalDateTime bloqueadoEm;

    @JsonProperty
    private String sistemaResponsavel;

    @JsonProperty
    private boolean ativo;

    public Bloqueio() {
    }

    public Bloqueio(BloqueioCartao bloqueioCartao) {
        this.id = bloqueioCartao.getUuid();
        this.bloqueadoEm = bloqueioCartao.getBloqueadoEm();
        this.sistemaResponsavel = bloqueioCartao.getSistemaResponsavel();
        this.ativo = bloqueioCartao.isAtivo();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getBloqueadoEm() {
        return bloqueadoEm;
    }

    public void setBloqueadoEm(LocalDateTime bloqueadoEm) {
        this.bloqueadoEm = bloqueadoEm;
    }

    public String getSistemaResponsavel() {
        return sistemaResponsavel;
    }

    public void setSistemaResponsavel(String sistemaResponsavel) {
        this.sistemaResponsavel = sistemaResponsavel;
    }

    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }
}
