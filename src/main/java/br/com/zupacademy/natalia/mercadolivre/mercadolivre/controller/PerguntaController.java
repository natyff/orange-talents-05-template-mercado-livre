package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.PerguntaRequest;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Pergunta;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.PerguntaRepository;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
public class PerguntaController {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    PerguntaRepository perguntaRepository;

    @Autowired
    Email email;

    @PostMapping("/produto/{id}/pergunta")
    public String novaPergunta(@RequestBody @Valid PerguntaRequest perguntaRequest, @PathVariable Long id,
                               @AuthenticationPrincipal Usuario pessoa, LocalDateTime data)  {
        Produto produto = produtoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Esse produto não existe"));
        Pergunta pergunta = perguntaRequest.converter(pessoa, produto, data);
        perguntaRepository.save(pergunta);
       email.enviaPergunta(pergunta);
        return pergunta.toString();
    }
}
