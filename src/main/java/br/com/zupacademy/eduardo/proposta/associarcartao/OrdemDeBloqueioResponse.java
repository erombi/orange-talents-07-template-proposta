package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

public class OrdemDeBloqueioResponse {

    private ResultadoBloqueio resultado;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public OrdemDeBloqueioResponse(ResultadoBloqueio resultado) {
        this.resultado = resultado;
    }

    public ResultadoBloqueio getResultado() {
        return resultado;
    }
}
