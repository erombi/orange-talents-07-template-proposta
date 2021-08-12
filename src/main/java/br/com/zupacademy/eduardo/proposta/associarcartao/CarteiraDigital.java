package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;

public class CarteiraDigital {

    private String id;
    private String email;
    private LocalDateTime associadoEm;
    private String emissor;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CarteiraDigital(String id, String email, LocalDateTime associadoEm, String emissor) {
        this.id = id;
        this.email = email;
        this.associadoEm = associadoEm;
        this.emissor = emissor;
    }
}
