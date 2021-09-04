package br.com.zupacademy.wallyson.mercadolivre.pergunta;

import br.com.zupacademy.wallyson.mercadolivre.produto.Produto;
import br.com.zupacademy.wallyson.mercadolivre.usuario.Usuario;

import javax.validation.constraints.NotBlank;

public class NovaPerguntaRequest {

    @NotBlank
    private String titulo;

    public String getTitulo() {
        return titulo;
    }

    public Pergunta toModel(Produto produto, Usuario usuario) {
        return new Pergunta(titulo, produto, usuario);
    }
}
