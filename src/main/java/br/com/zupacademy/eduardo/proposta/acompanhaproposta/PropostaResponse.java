package br.com.zupacademy.eduardo.proposta.acompanhaproposta;

import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.StatusProposta;
import br.com.zupacademy.eduardo.proposta.novaproposta.Proposta;

import java.math.BigDecimal;
import java.util.UUID;

public class PropostaResponse {

    private UUID id;
    private String documento;
    private String email;
    private String nome;
    private String endereco;
    private BigDecimal salario;
    private StatusProposta status;
    private String numeroCartao;

    public PropostaResponse(Proposta proposta) {
        this.id = proposta.getId();
        this.documento = proposta.getDocumento();
        this.email = proposta.getEmail();
        this.nome = proposta.getNome();
        this.endereco = proposta.getEndereco();
        this.salario = proposta.getSalario();
        this.status = proposta.getStatus();
        this.numeroCartao = proposta.getNumeroCartao();
    }

    public UUID getId() {
        return id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getEmail() {
        return email;
    }

    public String getNome() {
        return nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public BigDecimal getSalario() {
        return salario;
    }

    public StatusProposta getStatus() {
        return status;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }
}
