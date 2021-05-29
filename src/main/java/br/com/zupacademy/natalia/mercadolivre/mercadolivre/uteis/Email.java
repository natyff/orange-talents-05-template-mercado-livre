package br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Pergunta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Email {

    @Autowired
    private EnviaEmail enviaEmail;

    public void enviaPergunta(Pergunta pergunta){

        enviaEmail.enviar("titulo do email", "Assunto email", pergunta.getPessoa().getLogin(),
                pergunta.getAnunciante().getLogin());
    }


}
