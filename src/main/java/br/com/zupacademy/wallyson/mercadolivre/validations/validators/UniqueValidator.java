package br.com.zupacademy.wallyson.mercadolivre.validations.validators;

import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    @PersistenceContext
    private EntityManager entityManager;

    private Class<?> entity;
    private String attribute;
    private boolean optional;

    @Override
    public void initialize(Unique constraintAnnotation) {
        entity = constraintAnnotation.entity();
        attribute = constraintAnnotation.attribute();
        optional = constraintAnnotation.optional();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(value)) {
            return optional;
        }

        var jpql = String.format("SELECT e FROM %s e WHERE e.%s = :value", entity.getSimpleName(), attribute);
        var query = entityManager.createQuery(jpql);
        query.setParameter("value", value);
        var result = query.getResultList();

        return result.isEmpty();
    }
}
