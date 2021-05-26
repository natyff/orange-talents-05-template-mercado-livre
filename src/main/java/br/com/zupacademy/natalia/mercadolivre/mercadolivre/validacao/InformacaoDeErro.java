package br.com.zupacademy.natalia.mercadolivre.mercadolivre.validacao;

public class InformacaoDeErro {

    private String campo;
    private String mensagem;

    public InformacaoDeErro(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public InformacaoDeErro() {

    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}

