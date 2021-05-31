package br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Imagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotNull
    @ManyToOne
    private Produto produto;
    @URL
    @NotBlank
    private String link;


    public Imagem(Produto produto, String link) {
        this.produto = produto;
        this.link = link;
    }


    public String getLink() {
        return link;
    }

    public Imagem(){};

    @Override
    public String toString() {
        return "Imagem{" +
                "id=" + id +
                ", link='" + link + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Imagem)) return false;
        Imagem imagem = (Imagem) o;
        return Objects.equals(produto, imagem.produto) && Objects.equals(link, imagem.link);
    }

    @Override
    public int hashCode() {
        return Objects.hash(produto, link);
    }

}
