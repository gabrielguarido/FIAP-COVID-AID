package br.com.fiap.nac.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.GenericDAO;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;

/**
 * Classe responsável por realizar a implementação dos métodos do repositório DAO.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:34:55
 * @version 1.0
 */
public class GenericDAOImpl<T, K> implements GenericDAO<T, K> {

	/**
	 * Atributo em
	 */
	private EntityManager em;

	/**
	 * Atributo clazz
	 */
	private Class<T> clazz;

	/**
	 * Construtor da classe GenericDAOImpl
	 *
	 * @param em
	 */
	@SuppressWarnings("unchecked")
	public GenericDAOImpl(EntityManager em) {
		this.em = em;
		this.clazz = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Override
	public void update(T entity) {
		em.merge(entity);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<T> findOne(K id) {
		return (Optional<T>) em.find(clazz, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(K id) throws ResourceNotFoundException {
		Optional<T> entity = (Optional<T>) findOne(id).orElseThrow(() -> new ResourceNotFoundException());
		em.remove(entity);
	}

	@Override
	public void commit() throws CommitException {
		try {
			em.getTransaction().begin();
			em.getTransaction().commit();
		} catch (Exception e) {
			em.getTransaction().rollback();
			e.printStackTrace();
			throw new CommitException();
		}
	}

}
