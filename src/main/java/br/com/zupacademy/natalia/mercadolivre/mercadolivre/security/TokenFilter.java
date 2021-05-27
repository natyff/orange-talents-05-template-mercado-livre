package br.com.zupacademy.natalia.mercadolivre.mercadolivre.security;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.UsuarioRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class TokenFilter extends OncePerRequestFilter {  // essa classe é chamada uma unica vez a cada nova requisicao

    private TokenService tokenService;  // na classe filter nao consgio fazer injecao de dependencia, entao preciso criar um construtor
    private UsuarioRepository repository;

    public TokenFilter(TokenService tokenService, UsuarioRepository repository) {
        this.tokenService = tokenService;
        this.repository = repository;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse, FilterChain filterChain)
            throws ServletException, IOException {

        String token = recuperarToken(httpServletRequest);    //recuperar o token do cabeçalho para autenticar dps
        boolean valido = tokenService.isTokenValido(token);  // retorna se o token é valido ou nao
        if(valido){
            autenticarCliente(token);
        }
        filterChain.doFilter(httpServletRequest, httpServletResponse ); // essa linha informa para o Spring que ela já rodou o que tinha que rodar e para seguir com a requisicao
    }

    private void autenticarCliente(String token) { // aqui eu ja tenho o token e nao quero usuario e senha eu só quero que o Spring autentique o usuario

        Long idUsuario = tokenService.getIdUsuario(token);  // recuperando o ID do usuario (para saber quem é o usuario)
        Usuario usuario = repository.findById(idUsuario).get();
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);  // essa classe autentica o usuario, preciso passar quem é o usuario para ela
    }                                                           //para a variavel authentication é necessario criar o método (acima)

    private String recuperarToken(HttpServletRequest httpServletRequest) {

        String token = httpServletRequest.getHeader("Authorization"); //pega o token
        if (token == null || token.isEmpty() || !token.startsWith("Bearer ")) {// verifica se esta correto
            return null;
        }
        return token.substring(7, token.length());
    }
}

