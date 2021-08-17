package br.com.zupacademy.eduardo.proposta.associarcartao;

import br.com.zupacademy.eduardo.proposta.novaproposta.Proposta;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "tb_cartao")
public class Cartao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Column(unique = true)
    private String numeroCartao;

    private LocalDateTime emitidoEm;

    @NotBlank
    private String titular;

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<BloqueioCartao> bloqueioCartaos = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<AvisoViagem> avisos = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<CarteiraDigital> carteiras = new HashSet<>();

    @OneToMany(mappedBy = "cartao", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Parcela> parcelas = new HashSet<>();

    private Integer limite;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Renegociacao renegociacao;

    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Vencimento vencimento;

    @OneToOne(mappedBy = "cartao")
    private Proposta proposta;

    @Enumerated(EnumType.STRING)
    private StatusCartao statusCartao;

    @Deprecated
    public Cartao() {
    }

    public Cartao(CartaoResponse response) {
        this.numeroCartao = response.getId();
        this.emitidoEm = response.getEmitidoEm();
        this.titular = response.getTitular();

        if (!response.getBloqueios().stream().filter(Bloqueio::isAtivo).collect(Collectors.toSet()).isEmpty()) {
            this.bloqueioCartaos = response.getBloqueios().stream().map(BloqueioCartao::new).collect(Collectors.toSet());
            this.statusCartao = StatusCartao.BLOQUEADO;
        }
        else
            this.statusCartao = StatusCartao.EM_USO;

        if (!response.getAvisos().isEmpty())
            this.avisos = response.getAvisos().stream().map(AvisoViagem::new).collect(Collectors.toSet());

        if (!response.getCarteiras().isEmpty())
            this.carteiras = response.getCarteiras().stream().map(CarteiraDigital::new).collect(Collectors.toSet());

        if (!response.getParcelas().isEmpty())
            this.parcelas = response.getParcelas().stream().map(Parcela::new).collect(Collectors.toSet());

        this.limite = response.getLimite();

        if (response.getRenegociacao() != null)
            this.renegociacao = new Renegociacao(response.getRenegociacao());

        if (response.getVencimento() != null)
            this.vencimento = new Vencimento(response.getVencimento());

    }

    public Long getId() {
        return id;
    }

    public String getNumeroCartao() {
        return numeroCartao;
    }

    public LocalDateTime getEmitidoEm() {
        return emitidoEm;
    }

    public String getTitular() {
        return titular;
    }

    public Set<BloqueioCartao> getBloqueios() {
        return bloqueioCartaos;
    }

    public Set<AvisoViagem> getAvisos() {
        return avisos;
    }

    public Set<CarteiraDigital> getCarteiras() {
        return carteiras;
    }

    public Set<Parcela> getParcelas() {
        return parcelas;
    }

    public Integer getLimite() {
        return limite;
    }

    public Renegociacao getRenegociacao() {
        return renegociacao;
    }

    public Vencimento getVencimento() {
        return vencimento;
    }

    public UUID getProposta() {
        return proposta.getId();
    }

    public void atualizaBloqueio(OrdemDeBloqueioResponse response, String userAgent, String ip) {
        if (response.getResultado().equals(ResultadoBloqueio.BLOQUEADO)) {
            this.statusCartao = StatusCartao.BLOQUEADO;
            this.bloqueioCartaos.add(new BloqueioCartao(this, userAgent, ip));
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cartao cartao = (Cartao) o;
        return Objects.equals(numeroCartao, cartao.numeroCartao);
    }

    @Override
    public int hashCode() {
        return Objects.hash(numeroCartao);
    }
}
