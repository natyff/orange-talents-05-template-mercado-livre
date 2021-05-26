package br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao.ValorUnico;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank
    @Email
    @ValorUnico(domainClass = Usuario.class, fieldName = "login")
    private String login;
    @NotBlank
    private String senha;
    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy" )
    @PastOrPresent
    private LocalDateTime horaCadastro;

    public Usuario(){}


    public Usuario(String login, String senha) {
        this.login = login;
        this.senha = new BCryptPasswordEncoder().encode(senha);
        this.horaCadastro = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public LocalDateTime getHoraCadastro() {
        return horaCadastro;
    }
}




