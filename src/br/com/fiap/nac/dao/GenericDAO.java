package br.com.fiap.nac.dao;

import java.util.List;
import java.util.Optional;

import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;

/**
 * Interface respons�vel por criar um reposit�rio gen�rico.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:24:47
 * @version 1.0
 */
public interface GenericDAO<T, K> {

	/**
	 * M�todo respons�vel por persistir uma nova entidade.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param entity
	 */
	void save(T entity);

	/**
	 * M�todo respons�vel por atualizar uma entidade j� existente.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param entity
	 */
	void update(T entity);

	/**
	 * M�todo respons�vel por buscar uma entidade pelo ID informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 */
	Optional<T> findOne(K id);

	/**
	 * M�todo respons�vel por buscar todas as entidades cadastradas.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 */
	Optional<List<T>> findAll();

	/**
	 * M�todo respons�vel por remover uma entidade.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @throws ResourceNotFoundException
	 */
	void delete(K id) throws ResourceNotFoundException;

	/**
	 * M�todo respons�vel por commitar as altera��es realizadas em uma transa��o.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @throws CommitException
	 */
	void commit() throws CommitException;

}
