package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "tb_cartao_carteira_digital")
public class CarteiraDigital {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private String email;
    private LocalDateTime associadoEm;
    private String emissor;

    @ManyToOne
    private Cartao cartao;

    @Deprecated
    public CarteiraDigital() {
    }

    public CarteiraDigital(CarteiraDigitalResponse response) {
        this.uuid = response.getUuid();
        this.email = response.getEmail();
        this.associadoEm = response.getAssociadoEm();
        this.emissor = response.getEmissor();
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

    public Cartao getCartao() {
        return cartao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CarteiraDigital that = (CarteiraDigital) o;
        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
