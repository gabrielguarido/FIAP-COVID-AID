package br.com.fiap.nac.dao;

import java.util.Optional;

import br.com.fiap.nac.entity.Usuario;

/**
 * Classe respons�vel por implementar um reposit�rio para {@link Usuario}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:48:10
 * @version 1.0
 */
public interface UsuarioDAO extends GenericDAO<Usuario, Long> {

	/**
	 * M�todo respons�vel por buscar um {@link Usuario} filtrando pelo nome de usuario informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param username
	 * @return
	 */
	Optional<Usuario> findByUsername(final String username);

}
