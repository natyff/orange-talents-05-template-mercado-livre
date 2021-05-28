package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.ImagensRequest;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.ProdutoRequest;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.interf.Uploader;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao.CaracteristicaIgualValidator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Set;

@RestController
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    Uploader arquivosFake;


    @PersistenceContext
    EntityManager em;

    @InitBinder(value = "ProdutoRequest")
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

    @PostMapping("/{id}/imagens")
    public ResponseEntity <String> addImagens(@PathVariable @Valid Long id,  ImagensRequest imagensRequest){
        Usuario anunciante = em.find(Usuario.class,12L);
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Esse produto não existe"));
        if(!produto.pertenceAoUsuario(anunciante)){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        Set<String> links = arquivosFake.envia(imagensRequest.getImagens());

        produto.imagensAdd(links);
        produtoRepository.save(produto);
        return ResponseEntity.ok().build();
    }
}