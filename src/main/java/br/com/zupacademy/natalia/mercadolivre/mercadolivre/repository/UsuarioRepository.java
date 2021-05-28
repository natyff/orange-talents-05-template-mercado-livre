package br.com.zupacademy.natalia.mercadolivre.mercadolivre.repository;

import br.com.zupacademy.natalia.mercadolivre.mercadolivre.entities.Usuario;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {

    Optional<Usuario> findByLogin(String login);

}
