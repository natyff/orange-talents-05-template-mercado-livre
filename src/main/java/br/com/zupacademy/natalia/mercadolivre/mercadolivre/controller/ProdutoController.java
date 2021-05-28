package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;


import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.ProdutoRequest;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao.CaracteristicaIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;


    @PersistenceContext
    EntityManager em;

    @InitBinder
    public void init(WebDataBinder webDataBinder){
        webDataBinder.addValidators(new CaracteristicaIgualValidator());

    }

    @PostMapping
    public ResponseEntity<String> novoProduto(@RequestBody @Valid ProdutoRequest produtoRequest){
        Usuario anunciante = em.find(Usuario.class,12L);
        Produto produto = produtoRequest.converter(em, anunciante);
        produtoRepository.save(produto);
        return ResponseEntity.ok().body(produtoRequest.toString());
    }
}
