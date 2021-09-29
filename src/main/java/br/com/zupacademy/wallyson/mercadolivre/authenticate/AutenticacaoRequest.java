package br.com.zupacademy.wallyson.mercadolivre.authenticate;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import javax.validation.constraints.NotBlank;

public class AutenticacaoRequest {

    @NotBlank
    private String login;

    @NotBlank
    private String senha;

    public AutenticacaoRequest(String login, String senha) {
        this.login = login;
        this.senha = senha;
    }

    public UsernamePasswordAuthenticationToken toModel() {
        return new UsernamePasswordAuthenticationToken(login, senha);
    }
}
