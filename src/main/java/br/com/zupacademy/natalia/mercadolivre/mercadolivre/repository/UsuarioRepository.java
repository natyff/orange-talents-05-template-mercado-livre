package br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import org.springframework.data.repository.CrudRepository;


public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

}
