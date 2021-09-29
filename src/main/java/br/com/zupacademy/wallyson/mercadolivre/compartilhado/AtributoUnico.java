package br.com.zupacademy.wallyson.mercadolivre.compartilhado;

import br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao.Transacao;
import br.com.zupacademy.wallyson.mercadolivre.novacategoria.Categoria;
import br.com.zupacademy.wallyson.mercadolivre.novousuario.Usuario;

public enum AtributoUnico {
    USUARIO_LOGIN(Usuario.class, "login"),
    TRANSACAO_ID(Transacao.class, "transacaoId"),
    CATEGORIA_NOME(Categoria.class, "nome");

    private Class<?> classe;
    private String atributo;

    AtributoUnico(Class<?> classe, String atributo) {
        this.classe = classe;
        this.atributo = atributo;
    }

    public Class<?> getClasse() {
        return classe;
    }

    public String getAtributo() {
        return atributo;
    }
}
