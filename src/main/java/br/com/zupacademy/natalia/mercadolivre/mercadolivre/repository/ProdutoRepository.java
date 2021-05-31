package br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Produto;
import org.springframework.data.repository.CrudRepository;

public interface ProdutoRepository extends CrudRepository<Produto,Long> {
}
