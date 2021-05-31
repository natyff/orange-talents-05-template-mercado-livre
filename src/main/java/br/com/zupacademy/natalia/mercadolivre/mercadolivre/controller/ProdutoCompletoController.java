package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.ProdutoCompletoResponse;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController

public class ProdutoCompletoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/produto/{id}")
    public ResponseEntity<ProdutoCompletoResponse> mostraProduto(@PathVariable Long id){
      Optional<Produto> produto = produtoRepository.findById(id);
      if(produto.isPresent()){
        return ResponseEntity.ok(new ProdutoCompletoResponse(produto.get()));
      }
        return ResponseEntity.badRequest().build();
    }
}
