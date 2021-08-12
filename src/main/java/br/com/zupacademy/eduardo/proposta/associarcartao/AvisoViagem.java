package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDate;

public class AvisoViagem {

    private LocalDate validoAte;
    private String destino;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagem(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }
}
