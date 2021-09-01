package br.com.zupacademy.wallyson.mercadolivre.validations.annotations;

import br.com.zupacademy.wallyson.mercadolivre.validations.validators.ExistValidator;
import br.com.zupacademy.wallyson.mercadolivre.validations.validators.UniqueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = ExistValidator.class)
public @interface Exist {

    String message() default "Informe um valor que exista em nosso banco de dados.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> entity();

    boolean optional() default false;
}
