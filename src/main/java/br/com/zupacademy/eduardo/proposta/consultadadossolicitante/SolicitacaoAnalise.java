package br.com.zupacademy.eduardo.proposta.consultadadossolicitante;

import br.com.zupacademy.eduardo.proposta.novaproposta.Documento;
import br.com.zupacademy.eduardo.proposta.novaproposta.Proposta;

import javax.validation.constraints.NotBlank;

public class SolicitacaoAnalise {

    @Documento
    @NotBlank
    private String documento;

    @NotBlank
    private String nome;

    @NotBlank
    private String idProposta;

    public SolicitacaoAnalise(Proposta proposta) {
        this.documento = proposta.getDocumento();
        this.nome = proposta.getNome();
        this.idProposta = proposta.getId().toString();
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
}
