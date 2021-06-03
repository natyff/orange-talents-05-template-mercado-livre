package br.com.zupacademy.natalia.mercadolivre.mercadolivre.controller;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.dto.CompraParte1Request;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.CompraParte1;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.CompraParte1Repository;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository.ProdutoRepository;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis.Email;
import br.com.zupacademy.natalia.mercadolivre.mercadolivre.uteis.GatewayPagamento;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
public class CompraParte1Controller {

    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    CompraParte1Repository compraParte1Repository;

    @Autowired
    Email email;


    @PostMapping("/compra")
    public ResponseEntity<?> novaCompra(@RequestBody @Valid CompraParte1Request compraParte1Request, @AuthenticationPrincipal
                             Usuario comprador, UriComponentsBuilder uri) throws BindException {
      Produto comprado = produtoRepository.findById(compraParte1Request.getProdutoId()).get();
      Integer quantidade = compraParte1Request.getQuantidade();
        boolean estoqueDisponivel = comprado.retiraEstoque(compraParte1Request.getQuantidade());

        if(estoqueDisponivel) {
            GatewayPagamento gateway = compraParte1Request.getGateway();
            CompraParte1 compra = new CompraParte1(comprado, quantidade, comprador, gateway);
            compraParte1Repository.save(compra);

            email.enviaCompra(compra);

            URI novaURI = URI.create("/pagamento");
            return ResponseEntity.created(novaURI).body(compra.toString());
        }

            BindException problemaComEstoque = new BindException(compraParte1Request, "compraRequest");
            problemaComEstoque.reject(null,"Não foi possível realizar a compra devido ao estoque");

            throw problemaComEstoque;
        }
}
