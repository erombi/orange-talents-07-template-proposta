package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RespostaCarteiraDigital {

    private ResultadoCarteira resultado;
    private String id;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RespostaCarteiraDigital(ResultadoCarteira resultado, String id) {
        this.resultado = resultado;
        this.id = id;
    }

    public ResultadoCarteira getResultado() {
        return resultado;
    }

    public CarteiraDigital toModel(Cartao cartao, SolicitacaoCarteiraRequest request) {
        return new CarteiraDigital(this.id, request.getEmail(), request.getCarteira(), cartao);
    }
}
