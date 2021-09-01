package br.com.zupacademy.wallyson.mercadolivre.usuario;

import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    private String senha;

    private LocalDateTime createdAt = LocalDateTime.now();

    @Deprecated
    public Usuario() {
    }

    public Usuario(@NotBlank @Email @Unique(entity = Usuario.class, attribute = "login") String login,
                   @NotBlank @Size(min = 6) String senha) {
        this.login = login;
        this.senha = BCrypt.hashpw(senha, BCrypt.gensalt());
    }
}
