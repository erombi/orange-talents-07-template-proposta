package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "tb_cartao_vencimento")
public class Vencimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String uuid;
    private Integer dia;
    private LocalDateTime dataDeCriacao;

    @Deprecated
    public Vencimento() {
    }

    public Vencimento(VencimentoResponse response) {
        this.uuid = response.getId();
        this.dia = response.getDia();
        this.dataDeCriacao = response.getDataDeCriacao();
    }

    public String getUuid() {
        return uuid;
    }

    public Integer getDia() {
        return dia;
    }

    public LocalDateTime getDataDeCriacao() {
        return dataDeCriacao;
    }
}
