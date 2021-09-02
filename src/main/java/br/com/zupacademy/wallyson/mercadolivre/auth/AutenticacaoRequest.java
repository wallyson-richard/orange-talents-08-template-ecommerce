package br.com.zupacademy.wallyson.mercadolivre.auth;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class AutenticacaoRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }

    public UsernamePasswordAuthenticationToken toModel() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
