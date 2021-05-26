package br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao;

import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

    private String attibute;
    private Class<?> classe;
    @PersistenceContext
    private EntityManager em;

    @Override
    public void initialize(ValorUnico param) {
        attibute = param.fieldName();
        classe = param.domainClass();
    }

    @Override
    public boolean isValid(Object s, ConstraintValidatorContext constraintValidatorContext) {
        if(em != null){
            Query query = em.createQuery("select 1 from " + classe.getName()+ " where "+attibute+" =:value");
            query.setParameter("value", s);
            List<?> list = query.getResultList();
            Assert.state(list.size() <=1, "Existe mais de um registro com " + attibute + "igual" + classe );
            return list.isEmpty();
        }
        else{
            return Boolean.TRUE;
        }
    }
}
