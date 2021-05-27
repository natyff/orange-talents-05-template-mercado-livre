package br.com.zupacademy.natalia.mercadolivre.mercadolivre.security.swagger;


import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import java.util.Arrays;

@Configuration
public class Swagger {
    @Bean
    public Docket mercadoLivreApi() {
        return new Docket(DocumentationType.SWAGGER_2)  // indica qual o tipo de documentacão
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.zupacademy.natalia.mercadolivre.mercadolivre")) // a partir de qual pacote ele vai ler as classes
                .paths(PathSelectors.ant("/**")) // quais endpoints para fazer analize
                .build()
                .ignoredParameterTypes(Usuario.class) // para ignorar todas urls que trabalham com a classe usuário (para nao aparecer a senha)
                .globalOperationParameters(Arrays.asList(  // parametro que eu quero que o swagger apresente em todos os endpoints e ele recebe uma lista com todos os parametros
                        new ParameterBuilder() // informação de como vamos construir esse parametro
                                .name("Authorization")  // nome do parametro
                                .description("Header para token JWT") // descrição do que é esse parametro
                                .modelRef(new ModelRef("string")) // qual o tipo do parametro
                                .parameterType("header")
                                .required(false)
                                .build()));

    }
}

