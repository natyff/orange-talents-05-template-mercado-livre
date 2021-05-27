package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.LoginRequest;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.TokenDto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.security.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/login")
public class AutenticacaoController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    TokenService tokenService;

    @PostMapping
    public ResponseEntity<TokenDto> autenticacao (@RequestBody @Valid LoginRequest login){

        try{
            UsernamePasswordAuthenticationToken dadosLogin = login.converter();
            Authentication authentication = authenticationManager.authenticate(dadosLogin);

            String token = tokenService.gerarToken(authentication);
            System.out.println(token);

            return ResponseEntity.ok(new TokenDto(token,"Bearer")); //criar a clase DTO para encpsular as infos e levar para o cliente e falar para o cliente como ele vai fazer a autenticação nas proximas requisicos
        }catch (AuthenticationException e){
            return ResponseEntity.badRequest().build();
        }
    }
}

