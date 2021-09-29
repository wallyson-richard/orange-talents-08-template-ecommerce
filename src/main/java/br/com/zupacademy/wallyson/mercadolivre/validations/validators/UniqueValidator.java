package br.com.zupacademy.wallyson.mercadolivre.validations.validators;

import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;
import org.springframework.util.ObjectUtils;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueValidator implements ConstraintValidator<Unique, Object> {

    private final EntityManager entityManager;

    private Class<?> entity;
    private String attribute;
    private boolean optional;

    public UniqueValidator(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public void initialize(Unique constraintAnnotation) {
        entity = constraintAnnotation.value().getClasse();
        attribute = constraintAnnotation.value().getAtributo();
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
