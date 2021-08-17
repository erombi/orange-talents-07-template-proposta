package br.com.zupacademy.eduardo.proposta.associarcartao;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CartaoResponse {

    @JsonProperty
    private String id;
    @JsonProperty
    private LocalDateTime emitidoEm;
    @JsonProperty
    private String titular;
    @JsonProperty
    private List<Bloqueio> bloqueios = new ArrayList<>();
    @JsonProperty
    private Set<AvisoViagemResponse> avisos = new HashSet<>();
    @JsonProperty
    private Set<CarteiraDigitalResponse> carteiras = new HashSet<>();
    @JsonProperty
    private Set<ParcelaResponse> parcelas = new HashSet<>();
    @JsonProperty
    private Integer limite;
    @JsonProperty
    private RenegociacaoResponse renegociacao;
    @JsonProperty
    private VencimentoResponse vencimento;
    @JsonProperty
    private String idProposta;

    public CartaoResponse() {    }

    @JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
    public CartaoResponse(String id, LocalDateTime emitidoEm, String titular, List<Bloqueio> bloqueios, Set<AvisoViagemResponse> avisos, Set<CarteiraDigitalResponse> carteiras, Set<ParcelaResponse> parcelas, Integer limite, RenegociacaoResponse renegociacao, VencimentoResponse vencimento, String idProposta) {
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
        this.bloqueios = cartao.getBloqueios().stream().map(Bloqueio::new).collect(Collectors.toList());
        this.avisos = cartao.getAvisos().stream().map(AvisoViagemResponse::new).collect(Collectors.toSet());
        this.carteiras = cartao.getCarteiras().stream().map(CarteiraDigitalResponse::new).collect(Collectors.toSet());
        this.parcelas = cartao.getParcelas().stream().map(ParcelaResponse::new).collect(Collectors.toSet());
        this.limite = cartao.getLimite();
        if (cartao.getRenegociacao() != null)
            this.renegociacao = new RenegociacaoResponse(cartao.getRenegociacao());
        if (cartao.getVencimento() != null)
            this.vencimento = new VencimentoResponse(cartao.getVencimento());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public void setEmitidoEm(LocalDateTime emitidoEm) {
        this.emitidoEm = emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public List<Bloqueio> getBloqueios() {
        return bloqueios;
    }

    public void setBloqueios(List<Bloqueio> bloqueios) {
        this.bloqueios = bloqueios;
    }

    public Set<AvisoViagemResponse> getAvisos() {
        return avisos;
    }

    public void setAvisos(Set<AvisoViagemResponse> avisos) {
        this.avisos = avisos;
    }

    public Set<CarteiraDigitalResponse> getCarteiras() {
        return carteiras;
    }

    public void setCarteiras(Set<CarteiraDigitalResponse> carteiras) {
        this.carteiras = carteiras;
    }

    public Set<ParcelaResponse> getParcelas() {
        return parcelas;
    }

    public void setParcelas(Set<ParcelaResponse> parcelas) {
        this.parcelas = parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public void setLimite(Integer limite) {
        this.limite = limite;
    }

    public RenegociacaoResponse getRenegociacao() {
        return renegociacao;
    }

    public void setRenegociacao(RenegociacaoResponse renegociacao) {
        this.renegociacao = renegociacao;
    }

    public VencimentoResponse getVencimento() {
        return vencimento;
    }

    public void setVencimento(VencimentoResponse vencimento) {
        this.vencimento = vencimento;
    }

    public String getIdProposta() {
        return idProposta;
    }

    public void setIdProposta(String idProposta) {
        this.idProposta = idProposta;
    }
}
