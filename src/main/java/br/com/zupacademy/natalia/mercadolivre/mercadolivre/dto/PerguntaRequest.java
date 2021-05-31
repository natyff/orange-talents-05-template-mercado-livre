package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.CaracteristicaProduto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Pergunta;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

public class PerguntaRequest {

    @NotBlank
    private String titulo;
    private LocalDateTime criacao;


    public PerguntaRequest(String titulo, LocalDateTime criacao) {
        this.titulo = titulo;
        this.criacao = criacao;
    }

    public PerguntaRequest() {};


    public Pergunta converter(Usuario pessoa, Produto produto, LocalDateTime criacao) {
        return new Pergunta(this.titulo, pessoa, produto, criacao);
    }

    public String getTitulo() {
        return titulo;
    }

    public LocalDateTime getCriacao() {
        return criacao;
    }


}
