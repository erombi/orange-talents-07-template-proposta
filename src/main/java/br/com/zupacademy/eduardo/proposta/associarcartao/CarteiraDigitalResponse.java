package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

public class CarteiraDigitalResponse {

    private String uuid;
    private String email;
    private LocalDateTime associadoEm;
    private String emissor;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CarteiraDigitalResponse(String uuid, String email, LocalDateTime associadoEm, String emissor) {
        this.uuid = uuid;
        this.email = email;
        this.associadoEm = associadoEm;
        this.emissor = emissor;
    }

    public CarteiraDigitalResponse(CarteiraDigital carteiraDigital) {
        this.uuid = carteiraDigital.getUuid();
        this.email = carteiraDigital.getEmail();
        this.associadoEm = carteiraDigital.getAssociadoEm();
        this.emissor = carteiraDigital.getEmissor();
    }

    public String getUuid() {
        return uuid;
    }

    public String getEmail() {
        return email;
    }

    public LocalDateTime getAssociadoEm() {
        return associadoEm;
    }

    public String getEmissor() {
        return emissor;
    }
}
