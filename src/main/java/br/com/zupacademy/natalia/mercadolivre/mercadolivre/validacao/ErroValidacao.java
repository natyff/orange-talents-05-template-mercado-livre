package br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class ErroValidacao {

    @Autowired
    private MessageSource messageSource;

    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public List<InformacaoDeErro> handle(MethodArgumentNotValidException exception){
        List<InformacaoDeErro> dto = new ArrayList<>();

        List<FieldError> fieldErrors = exception.getBindingResult().getFieldErrors();
        fieldErrors.forEach(e ->{
            String mensagem = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            InformacaoDeErro erro = new InformacaoDeErro(e.getField(), mensagem);
            dto.add(erro);
        });
        return dto;
    }
}