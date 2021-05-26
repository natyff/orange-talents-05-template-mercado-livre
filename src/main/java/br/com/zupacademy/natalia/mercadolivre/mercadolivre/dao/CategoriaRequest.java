package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dao;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Categoria;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.CategoriasRepository;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao.ExistId;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao.ValorUnico;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import java.util.Optional;

public class CategoriaRequest {

    @NotBlank
    @ValorUnico(domainClass = Categoria.class, fieldName = "nome")
    private String nome;

    @ExistId(domainClass = Categoria.class, fieldValue = "id")
    @ManyToOne
    private Long categoriaMaeId;

    public CategoriaRequest(String nome) {
        this.nome = nome;
    }

    public CategoriaRequest(String nome, Long categoriaMaeId) {
        this.nome = nome;
        this.categoriaMaeId = categoriaMaeId;
    }

    public String getNome() {
        return nome;
    }

    public Long getCategoriaMaeId() {
        return categoriaMaeId;
    }


    public Categoria converter(CategoriasRepository categoriasRepository){
        Categoria categoriaDiferenteDaCategoriaRequestEsseAquiEUmaEntity = new Categoria(this);
        if(this.categoriaMaeId !=null){
            Optional<Categoria> cat = categoriasRepository.findById(this.categoriaMaeId);
            if(cat.isPresent()){
                categoriaDiferenteDaCategoriaRequestEsseAquiEUmaEntity.setCategoriaMaeId(cat.get());
            }
        }return categoriaDiferenteDaCategoriaRequestEsseAquiEUmaEntity;
    }
}

