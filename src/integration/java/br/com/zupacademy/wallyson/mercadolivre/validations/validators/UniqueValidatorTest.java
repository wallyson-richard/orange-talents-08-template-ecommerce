package br.com.zupacademy.wallyson.mercadolivre.validations.validators;

import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;
import br.com.zupacademy.wallyson.mercadolivre.usuario.UsuarioRepository;
import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@SpringBootTest
@ActiveProfiles("test")
public class UniqueValidatorTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private Validator validator;

    @BeforeEach
    public void setup() {
        usuarioRepository.deleteAll();
    }

    @Test
    public void seAtributoExistirNaoValidar() {
        // Cenário
        usuarioRepository.save(new Usuario("wallyson@email.com", "123456"));
        // Ação
        Set<ConstraintViolation<Entity>> violations = validator.validate(new Entity("wallyson@email.com"));
        // Validacao
        Assertions.assertEquals(1, violations.size());
    }

    @Test
    public void seAtributoExistirValidar() {
        // Ação
        Set<ConstraintViolation<Entity>> violations = validator.validate(new Entity("wally@gmail.com"));
        // Validacao
        Assertions.assertEquals(0, violations.size());
    }

    @Test
    public void seAtributoForNuloENaoOpcionalNaoValidar() {
        // Ação
        Set<ConstraintViolation<Entity>> violations = validator.validate(new Entity(null));
        // Validacao
        Assertions.assertEquals(1, violations.size());
    }

    @Test
    public void seAtributoForNuloEOpcionalValidar() {
        // Ação
        Set<ConstraintViolation<EntityAttributeOptional>> violations = validator.validate(new EntityAttributeOptional(null));
        // Validacao
        Assertions.assertEquals(0, violations.size());
    }

    static class Entity {
        @Unique(entity = Usuario.class, attribute = "login")
        private String login;

        public Entity(String login) {
            this.login = login;
        }
    }

    static class EntityAttributeOptional {
        @Unique(entity = Usuario.class, attribute = "login", optional = true)
        private String login;

        public EntityAttributeOptional(String login) {
            this.login = login;
        }
    }
}

