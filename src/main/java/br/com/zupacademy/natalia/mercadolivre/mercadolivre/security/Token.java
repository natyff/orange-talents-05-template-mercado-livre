package br.com.zupacademy.natalia.mercadolivre.mercadolivre.security;


import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class Token {

    //para injetar parametros do application
    @Value("${mercadolivre.jwt.expiration}")
    private String expiration;

    @Value("${mercadolivre.jwt.secret}")
    private String secret;


    public String gerarToken(Authentication authentication) {
        Usuario logado = (Usuario) authentication.getPrincipal();  //recupero o usuario que esta logado
        Date hoje = new Date();  // date que criei para IssuedAt
        Date dataExpiracao = new Date(hoje.getTime()+ Long.parseLong(expiration)); // ele ira pegar a conf do meu properties e somar com a data de hoje
        return Jwts.builder() // api para gerar o token
                .setIssuer("Mercado Livre API")  //quem está gerando o token
                .setSubject(logado.getId().toString()) // o usuario a quem o token pertence
                .setIssuedAt(hoje)  // data de geração do token (preciso gerar um date)
                .setExpiration(dataExpiracao) //tempo de expiração do token(precisamos criar     outra data e além disso configurar no properties)
                .signWith(SignatureAlgorithm.HS256, secret) // aqui fala que o token tem que ser criptografado entao eu passo quem é o algoritimo e a senha da minha aplicacao (que é meu secret)
                .compact(); //para compactar e transformar em uma string
    }

    public boolean isTokenValido(String token) {
        try {
            Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }

    }

    public Long getIdUsuario(String token) {
        Claims claims = Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
        return  Long.parseLong(claims.getSubject());
    }
}