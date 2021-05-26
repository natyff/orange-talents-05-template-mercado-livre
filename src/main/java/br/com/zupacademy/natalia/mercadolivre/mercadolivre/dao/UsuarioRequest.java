package br.com.zupacademy.natalia.mercadolivre.mercadolivre.dao;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao.ValorUnico;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UsuarioRequest {

    @NotBlank
    @Email
    @ValorUnico(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank
    @Length(min = 6)
    private String senha;

    public UsuarioRequest(@NotBlank @Email String login, @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario converter(){
        return new Usuario(this.login, this.senha);
    };

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
}
