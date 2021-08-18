package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

public class RespostaAvisoViagem {

    private ResultadoAviso resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public RespostaAvisoViagem(ResultadoAviso resultado) {
        this.resultado = resultado;
    }

    public ResultadoAviso getResultado() {
        return resultado;
    }
}
