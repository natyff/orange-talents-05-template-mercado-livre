package br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ValorUnicoValidator.class )
public @interface ValorUnico {


    String message() default "Este cadastro está duplicado";
    Class<?>[] groups() default { };
    Class<? extends Payload> [] payload() default {};

    String fieldName();
    Class<?> domainClass();
}
