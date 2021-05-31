package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.OpiniaoProduto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


public class OpiniaoProdutoRequest {

    @NotNull
    @Min(1)  @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;


    public OpiniaoProdutoRequest(Integer nota, String titulo, String descricao) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
    }

    public OpiniaoProdutoRequest() {
    }

    public OpiniaoProduto converter(Usuario comprador, Produto produto){
        return new OpiniaoProduto(this.nota, this.titulo, this.descricao, comprador, produto);
    }

    public Integer getNota() {
        return nota;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    @Override
    public String toString() {
        return "OpiniaoProdutoRequest{" +
                "nota=" + nota +
                ", titulo='" + titulo + '\'' +
                ", descricao='" + descricao + '\'' +
                '}';
    }
}
