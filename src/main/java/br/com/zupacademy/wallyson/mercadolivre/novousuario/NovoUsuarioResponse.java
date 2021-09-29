package br.com.zupacademy.wallyson.mercadolivre.novousuario;

import java.time.LocalDateTime;

public class NovoUsuarioResponse {

    private Long id;

    private String login;

    private String criadoEm;

    public NovoUsuarioResponse(Long id, String login, String criadoEm) {
        this.id = id;
        this.login = login;
        this.criadoEm = criadoEm;
    }

    public Long getId() {
        return id;
    }

    public String getLogin() {
        return login;
    }

    public String getCriadoEm() {
        return criadoEm;
    }
}
