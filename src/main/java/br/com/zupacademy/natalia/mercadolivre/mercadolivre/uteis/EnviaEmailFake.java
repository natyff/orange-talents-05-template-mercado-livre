package br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class EnviaEmailFake implements EnviaEmail {
    @Override
    public void enviar(String titulo, String assunto, String emailCliente, String emailAnunciante) {
        System.out.println(titulo);
        System.out.println(assunto);
        System.out.println(emailCliente);
        System.out.println(emailAnunciante);

    }
}
