package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto;


import br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis.GatewayPagamento;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

public class CompraParte1Request {

    @Positive
    @Min(1)
    private Integer quantidade;
    @NotNull
    private Long produtoId;
    @NotNull
    private GatewayPagamento gateway;



    public Integer getQuantidade() {
        return quantidade;
    }

    public Long getProdutoId() {
        return produtoId;
    }

    public GatewayPagamento getGateway() {
        return gateway;
    }
}
