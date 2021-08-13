package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class CartaoResponse {

    private String id;
    private LocalDateTime emitidoEm;
    private String titular;
    private Set<BloqueioResponse> bloqueios = new HashSet<>();
    private Set<AvisoViagemResponse> avisos = new HashSet<>();
    private Set<CarteiraDigitalResponse> carteiras = new HashSet<>();
    private Set<ParcelaResponse> parcelas = new HashSet<>();
    private Integer limite;
    private RenegociacaoResponse renegociacao;
    private VencimentoResponse vencimento;
    private String idProposta;

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CartaoResponse(String id, LocalDateTime emitidoEm, String titular, Set<BloqueioResponse> bloqueios,
                            Set<AvisoViagemResponse> avisos, Set<CarteiraDigitalResponse> carteiras, Set<ParcelaResponse> parcelas,
                            Integer limite, RenegociacaoResponse renegociacao, VencimentoResponse vencimento, String idProposta) {
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

    public CartaoResponse(Cartao cartao) {
        this.id = cartao.getNumeroCartao();
        this.emitidoEm = cartao.getEmitidoEm();
        this.titular = cartao.getTitular();
        this.bloqueios = cartao.getBloqueios().stream().map(BloqueioResponse::new).collect(Collectors.toSet());
        this.avisos = cartao.getAvisos().stream().map(AvisoViagemResponse::new).collect(Collectors.toSet());
        this.carteiras = cartao.getCarteiras().stream().map(CarteiraDigitalResponse::new).collect(Collectors.toSet());
        this.parcelas = cartao.getParcelas().stream().map(ParcelaResponse::new).collect(Collectors.toSet());
        this.limite = cartao.getLimite();
        this.renegociacao = new RenegociacaoResponse(cartao.getRenegociacao());
        this.vencimento = new VencimentoResponse(cartao.getVencimento());
    }



    public String getId() {
        return id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Set<BloqueioResponse> getBloqueios() {
        return bloqueios;
    }

    public Set<AvisoViagemResponse> getAvisos() {
        return avisos;
    }

    public Set<CarteiraDigitalResponse> getCarteiras() {
        return carteiras;
    }

    public Set<ParcelaResponse> getParcelas() {
        return parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public RenegociacaoResponse getRenegociacao() {
        return renegociacao;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }
}
