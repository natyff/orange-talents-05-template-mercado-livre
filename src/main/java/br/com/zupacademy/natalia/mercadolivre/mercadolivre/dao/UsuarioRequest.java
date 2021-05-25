package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dao;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UsuarioRequest {

    @NotBlank
    @Email
    private String login;
    @NotBlank
    @Size(min = 6)
    private String senha;

    public UsuarioRequest(@NotBlank String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converter(){
        return new Usuario(this.login, this.senha);
    };

}
