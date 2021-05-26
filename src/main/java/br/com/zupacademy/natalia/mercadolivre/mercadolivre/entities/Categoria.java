package br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dao.CategoriaRequest;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    private String nome;

    @ManyToOne
    private Categoria categoriaMaeId;

    public Categoria(){};


    public Categoria(CategoriaRequest request) {
        this.nome = request.getNome();
    }

    public void setCategoriaMaeId(Categoria categoriaMaeId) {
        this.categoriaMaeId = categoriaMaeId;
    }
}
