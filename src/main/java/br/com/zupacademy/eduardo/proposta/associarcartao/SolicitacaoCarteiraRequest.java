package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class SolicitacaoCarteiraRequest {

    @NotBlank
    private String email;

    @NotNull
    private TipoCarteira carteira;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public SolicitacaoCarteiraRequest(String email, TipoCarteira carteira) {
        this.email = email;
        this.carteira = carteira;
    }

    public String getEmail() {
        return email;
    }

    public TipoCarteira getCarteira() {
        return carteira;
    }
}
