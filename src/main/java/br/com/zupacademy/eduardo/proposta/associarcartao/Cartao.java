package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Cartao {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private Set<Bloqueio> bloqueios = new HashSet<>();
    private Set<AvisoViagem> avisos = new HashSet<>();
    private Set<CarteiraDigital> carteiras = new HashSet<>();
    private Set<Parcela> parcelas = new HashSet<>();
    private Integer limite;
    private Renegociacao renegociacao;
    private Vencimento vencimento;
    private String idProposta;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public Cartao(String id, LocalDateTime emitidoEm, String titular, Set<Bloqueio> bloqueios, Set<AvisoViagem> avisos, Set<CarteiraDigital> carteiras, Set<Parcela> parcelas, Integer limite, Renegociacao renegociacao, Vencimento vencimento, String idProposta) {
        this.id = id;
        this.emitidoEm = emitidoEm;
        this.titular = titular;
        this.bloqueios = bloqueios;
        this.avisos = avisos;
        this.carteiras = carteiras;
        this.parcelas = parcelas;
        this.limite = limite;
        this.renegociacao = renegociacao;
        this.vencimento = vencimento;
        this.idProposta = idProposta;
    }

    public String getId() {
        return id;
    }
}
