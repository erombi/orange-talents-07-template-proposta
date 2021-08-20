package br.com.zupacademy.eduardo.proposta.associarcartao;

import java.time.LocalDate;

public class AvisoViagemResponse {

    private LocalDate validoAte;
    private String destino;

    public AvisoViagemResponse() {
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

    public void setValidoAte(LocalDate validoAte) {
        this.validoAte = validoAte;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }
}
