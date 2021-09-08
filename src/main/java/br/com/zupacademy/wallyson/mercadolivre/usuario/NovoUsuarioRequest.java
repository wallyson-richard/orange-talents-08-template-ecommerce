package br.com.zupacademy.wallyson.mercadolivre.usuario;

import br.com.zupacademy.wallyson.mercadolivre.validations.annotations.Unique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class NovoUsuarioRequest {

    @NotBlank
    @Email
    @Unique(entity = Usuario.class, attribute = "login")
    private String login;

    @NotBlank
    @Size(min = 6)
    private String senha;

    public NovoUsuarioRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public Usuario toModel() {
        return new Usuario(login, senha);
    }
}
