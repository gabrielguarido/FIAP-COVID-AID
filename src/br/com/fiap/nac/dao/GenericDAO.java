package br.com.fiap.nac.dao;

import java.util.List;
import java.util.Optional;

import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;

/**
 * Interface responsável por criar um repositório genérico.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:24:47
 * @version 1.0
 */
public interface GenericDAO<T, K> {

	/**
	 * Método responsável por persistir uma nova entidade.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param entity
	 */
	void save(T entity);

	/**
	 * Método responsável por atualizar uma entidade já existente.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param entity
	 */
	void update(T entity);

	/**
	 * Método responsável por buscar uma entidade pelo ID informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 */
	Optional<T> findOne(K id);

	/**
	 * Método responsável por buscar todas as entidades cadastradas.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 */
	Optional<List<T>> findAll();

	/**
	 * Método responsável por remover uma entidade.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @throws ResourceNotFoundException
	 */
	void delete(K id) throws ResourceNotFoundException;

	/**
	 * Método responsável por commitar as alterações realizadas em uma transação.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @throws CommitException
	 */
	void commit() throws CommitException;

}
