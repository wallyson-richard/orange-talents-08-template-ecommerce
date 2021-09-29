package br.com.zupacademy.wallyson.mercadolivre.novacompra.transacao;

public enum StatusTransacao {
    ERRO,
    SUCESSO;

    public static StatusTransacao getStatusTransacao(Byte status) {
        if (status == 0) {
            return  ERRO;
        }
        return  SUCESSO;
    }
}
