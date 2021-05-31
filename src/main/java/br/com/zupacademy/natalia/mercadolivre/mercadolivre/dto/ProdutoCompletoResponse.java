package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Imagem;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

public class ProdutoCompletoResponse {

    private List<String> imagens = new ArrayList<>();
    private String nomeProduto;
    private BigDecimal precoProduto;
    private List<CaracteristicaRequest> caracteristicas = new ArrayList<>();
    private String descricao;
    private List<OpiniaoProdutoRequest> opinicoes = new ArrayList<>();
    private List<PerguntaRequest> perguntas = new ArrayList<>();
    private Long somaNotas;
    private Double mediaNotas;
    private Map<Integer, Long> quantidadeOpinioesPorNota;


    public ProdutoCompletoResponse(Produto produto){
        this.nomeProduto = produto.getNome();
        this.precoProduto = produto.getValor();
        this.descricao = produto.getDescricao();
        this.imagens.addAll(
                produto.getImagens().stream().map(Imagem::getLink)
        .collect(Collectors.toList()));
        this.caracteristicas.addAll(
                produto.getCaracteristicas().stream()
                        .map(item -> new CaracteristicaRequest(item.getNome(), item.getDescricao()))
                        .collect(Collectors.toList()));
        this.perguntas.addAll(
                produto.getPerguntas().stream()
                        .map(pergunta -> new PerguntaRequest(pergunta.getTitulo(), pergunta.getCriacao()))
                        .collect(Collectors.toList()));
        this.opinicoes.addAll(
                produto.getOpiniaoProdutos().stream()
                        .map(opiniao -> new OpiniaoProdutoRequest(opiniao.getNota(), opiniao.getTitulo(),
                                opiniao.getDescricao()))
                        .collect(Collectors.toList()));
        this.somaNotas = produto.somaNotas();
        this.quantidadeOpinioesPorNota = produto.totalNotas();
        this.mediaNotas = produto.mediaNotas();
    }


    public String getNomeProduto() {
        return nomeProduto;
    }

    public BigDecimal getPrecoProduto() {
        return precoProduto;
    }

    public String getDescricao() {
        return descricao;
    }

    public List<CaracteristicaRequest> getCaracteristicas() {
        return caracteristicas;
    }

    public List<PerguntaRequest> getPerguntas() {
        return perguntas;
    }

    public List<OpiniaoProdutoRequest> getOpinicoes() {
        return opinicoes;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public Long getSomaNotas() {
        return somaNotas;
    }

    public Double getMediaNotas() {
        return mediaNotas;
    }

    public Map<Integer, Long> getQuantidadeOpinioesPorNota() {
        return quantidadeOpinioesPorNota;
    }

    public ProdutoCompletoResponse() {
    }
}
