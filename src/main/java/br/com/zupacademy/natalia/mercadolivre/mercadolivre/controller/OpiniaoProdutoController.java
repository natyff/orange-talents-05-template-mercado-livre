package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.OpiniaoProdutoRequest;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.OpiniaoProduto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.OpiniaoProdutoRepository;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;

@RestController
public class OpiniaoProdutoController {

    @Autowired
    OpiniaoProdutoRepository opiniaoProdutoRepository;

    @Autowired
    ProdutoRepository produtoRepository;


    @PostMapping("/produto/{id}/opiniao")
    public String novaOpiniao (@RequestBody @Valid OpiniaoProdutoRequest opiniao, @PathVariable Long id,
                               @AuthenticationPrincipal Usuario comprador){
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Esse produto não existe"));
        OpiniaoProduto opiniaoProduto = opiniao.converter(comprador, produto);
        opiniaoProdutoRepository.save(opiniaoProduto);
        return opiniaoProduto.toString();
    }
}
