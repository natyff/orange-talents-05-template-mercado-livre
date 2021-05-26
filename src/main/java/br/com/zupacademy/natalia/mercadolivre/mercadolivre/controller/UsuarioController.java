package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dao.UsuarioRequest;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/cadastro")
public class UsuarioController {

    @Autowired
    UsuarioRepository usuarioRepository;


    @PostMapping
    public ResponseEntity<String> novoUsuario (@RequestBody @Valid UsuarioRequest usuarioRequest){
        Usuario usuario = usuarioRequest.converter();
        usuarioRepository.save(usuario);
        return ResponseEntity.ok().build();
        }
}
