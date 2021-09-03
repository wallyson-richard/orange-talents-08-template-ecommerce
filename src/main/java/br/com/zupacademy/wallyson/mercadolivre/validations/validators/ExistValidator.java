package br.com.zupacademy.wallyson.mercadolivre.validations.validators;

import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Exist;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ExistValidator implements ConstraintValidator<Exist, Object> {

    private final EntityManager entityManager;

    private Class<?> entity;
    private boolean optional;

    public ExistValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(Exist constraintAnnotation) {
        entity = constraintAnnotation.entity();
        optional = constraintAnnotation.optional();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (ObjectUtils.isEmpty(value)) {
            return optional;
        }

        var jpql = String.format("SELECT e FROM %s e WHERE e.id = :value", entity.getSimpleName());
        var query = entityManager.createQuery(jpql);
        query.setParameter("value", value);
        var result = query.getResultList();

        return !result.isEmpty();
    }
}
