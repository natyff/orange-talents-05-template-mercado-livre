package br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao;


import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.ProdutoRequest;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import java.util.Set;

public class CaracteristicaIgualValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return ProdutoRequest.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        if(errors.hasErrors()){
            return;
        }
        ProdutoRequest produtoRequest = (ProdutoRequest) o;
        Set<String> nomesIguais = produtoRequest.mesmaCaracteristica();
        if(!nomesIguais.isEmpty()){
            errors.rejectValue("caracteristicas", null, "Essa caracteristica está repetida "+nomesIguais);
        }
    }
}
