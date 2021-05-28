package br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Entity
public class OpiniaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @Min(1)  @Max(5)
    private Integer nota;
    @NotBlank
    private String titulo;
    @NotBlank
    @Length(max = 500)
    private String descricao;
    @NotNull
    @ManyToOne
    @Valid
    private Usuario comprador;
    @NotNull
    @ManyToOne
    @Valid
    private Produto produto;

    public OpiniaoProduto(Integer nota, String titulo, String descricao, Usuario comprador, Produto produto) {
        this.nota = nota;
        this.titulo = titulo;
        this.descricao = descricao;
        this.comprador = comprador;
        this.produto = produto;
    }
}
