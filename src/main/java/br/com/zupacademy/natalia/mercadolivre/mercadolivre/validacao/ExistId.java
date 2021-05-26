package br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistIdValidator.class )
public @interface ExistId {

    String message() default "O ID informado não existe";
    Class<?>[] groups() default { };
    public abstract Class<? extends Payload> [] payload() default {};

    String fieldValue();
    Class<?> domainClass();
}
