package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dao.CategoriaRequest;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Categoria;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.CategoriasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/categorias")
public class CategoriasController {

    @Autowired
    CategoriasRepository categoriasRepository;

    @PostMapping
    public ResponseEntity<String> novaCategoria(@RequestBody @Valid CategoriaRequest categoriaRequest){
        Categoria categoria = categoriaRequest.converter(categoriasRepository);
        categoriasRepository.save(categoria);
        return ResponseEntity.ok().build();
    }
}

