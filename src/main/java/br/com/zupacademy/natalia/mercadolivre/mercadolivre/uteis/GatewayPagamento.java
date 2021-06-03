package br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.CompraParte1;
import org.springframework.web.util.UriComponentsBuilder;

public enum GatewayPagamento {

    pagseguro{
        @Override
        public String criaUrl(CompraParte1 compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPagseguro = uriComponentsBuilder.path("/retorno-pagseguro/{id}")
                    .buildAndExpand(compra.getId()).toString();
            return "pagseguro.com?buyerId=" + compra.getId() + "&redirectUrl="
                    + urlRetornoPagseguro;
        }
    },

    paypal {
        @Override
        public String criaUrl(CompraParte1 compra, UriComponentsBuilder uriComponentsBuilder) {
            String urlRetornoPaypal = uriComponentsBuilder.path("/retorno-paypal/{id}")
                    .buildAndExpand(compra.getId()).toString();
            return "paypal.com?buyerId=" + compra.getId() + "&redirectUrl=" + urlRetornoPaypal;
        }
    };

     public abstract String criaUrl(CompraParte1 compra, UriComponentsBuilder uriComponentsBuilder);

}


