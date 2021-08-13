package br.com.zupacademy.eduardo.proposta.novaproposta;

import br.com.zupacademy.eduardo.proposta.associarcartao.Cartao;
import br.com.zupacademy.eduardo.proposta.associarcartao.CartaoResponse;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.ResultadoSolicitacao;
import br.com.zupacademy.eduardo.proposta.consultadadossolicitante.StatusProposta;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "tb_proposta")
public class Proposta {

    @Id
    private UUID id;

    @NotBlank
    @Documento
    private String documento;

    @NotBlank
    @Email
    private String email;

    @NotBlank
    private String nome;

    @NotBlank
    private String endereco;

    @NotNull
    @Positive
    private BigDecimal salario;

    @NotNull
    @Enumerated(EnumType.STRING)
    private StatusProposta status;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Cartao cartao;

    @Deprecated
    public Proposta() {
    }

    public Proposta(@NotBlank @Documento String documento, @NotBlank @Email String email, @NotBlank String nome,
                    @NotBlank String endereco, @NotNull @Positive BigDecimal salario) {
        this.id = UUID.randomUUID();
        this.documento = documento;
        this.email = email;
        this.nome = nome;
        this.endereco = endereco;
        this.salario = salario;
        this.status = StatusProposta.NAO_ELEGIVEL;
    }

    public UUID getId() {
        return this.id;
    }

    public String getDocumento() {
        return documento;
    }

    public String getNome() {
        return nome;
    }

    public String getEmail() {
        return email;
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

    public Cartao getCartao() {
        return cartao;
    }

    public void atualizaStatus(ResultadoSolicitacao resultadoSolicitacao) {
        this.status = resultadoSolicitacao.getStatusProposta();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Proposta proposta = (Proposta) o;
        return Objects.equals(documento, proposta.documento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(documento);
    }

    public void associaCartao(Cartao cartao) {
        this.cartao = cartao;
    }
}
