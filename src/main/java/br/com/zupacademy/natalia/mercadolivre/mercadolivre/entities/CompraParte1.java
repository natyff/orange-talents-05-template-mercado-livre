package br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis.GatewayPagamento;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis.StatusCompra;
import org.springframework.web.util.UriComponentsBuilder;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Entity
public class CompraParte1 {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    @NotNull
    private Produto comprado;
    @Positive
    @Min(1)
    private Integer quantidade;
    @ManyToOne
    @NotNull
    private Usuario comprador;
    @Enumerated
    @NotNull
    private GatewayPagamento gateway;
    @Enumerated
    @NotNull
    private StatusCompra status = StatusCompra.INICIADA;
    @Positive
    @NotNull
    private BigDecimal preço;

    public CompraParte1(Produto comprado, Integer quantidade, Usuario comprador, GatewayPagamento gateway) {
        this.comprado = comprado;
        this.quantidade = quantidade;
        this.comprador = comprador;
        this.gateway = gateway;
        this.preço = comprado.getValor();
    }

    public Long getId() {
        return id;
    }

    public Produto getComprado() {
        return comprado;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Usuario getComprador() {
        return comprador;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }

    public Usuario getVendedor() {
        return this.comprado.getAnunciante();
    }

    public String urlRetorno(UriComponentsBuilder uriComponentsBuilder) {
        return this.gateway.criaUrl(this, uriComponentsBuilder);
    }

    @Override
    public String toString() {
        return "CompraParte1{" +
                "id=" + id +
                ", comprado=" + comprado +
                ", quantidade=" + quantidade +
                ", comprador=" + comprador +
                ", gateway=" + gateway +
                ", status=" + status +
                ", preço=" + preço +
                '}';
    }

}
