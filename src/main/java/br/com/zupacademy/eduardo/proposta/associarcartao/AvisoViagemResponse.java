package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;

public class AvisoViagemResponse {

    private LocalDate validoAte;
    private String destino;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public AvisoViagemResponse(LocalDate validoAte, String destino) {
        this.validoAte = validoAte;
        this.destino = destino;
    }

    public AvisoViagemResponse(AvisoViagem avisoViagem) {
        this.validoAte = avisoViagem.getValidoAte();
        this.destino = avisoViagem.getDestino();
    }

    public LocalDate getValidoAte() {
        return validoAte;
    }

    public String getDestino() {
        return destino;
    }
}
