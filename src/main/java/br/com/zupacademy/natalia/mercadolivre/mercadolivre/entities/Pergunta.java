package br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;


@Entity
public class Pergunta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String titulo;
    @NotNull
    @ManyToOne
    private Usuario pessoa;
    @NotNull
    @ManyToOne
    private Produto produto;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy" )
    private LocalDateTime criacao;


    public Pergunta(String titulo, Usuario pessoa, Produto produto, LocalDateTime criacao) {
        this.titulo = titulo;
        this.pessoa = pessoa;
        this.produto = produto;
        this.criacao = LocalDateTime.now();

    }

    @Override
    public String toString() {
        return "Pergunta{" +
                "id=" + id +
                ", titulo='" + titulo + '\'' +
                ", pessoa=" + pessoa +
                ", produto=" + produto +
                ", criacao=" + criacao +
                '}';
    }

    public Usuario getPessoa() {
        return pessoa;
    }

    public Usuario getAnunciante() {
        return produto.getAnunciante();
    }
}
