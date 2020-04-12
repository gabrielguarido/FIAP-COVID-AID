package br.com.fiap.nac.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;

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
	protected EntityManager em;

	/**
	 * Atributo clazz
	 */
	private Class<T> clazz;

	/**
	 * Construtor da classe GenericDAOImpl
	 *
	 * @param em
	 */
	public GenericDAOImpl(EntityManager em) {
		this.em = em;
		this.clazz = this.getTypeClass();
	}

	@Override
	public void save(T entity) {
		em.persist(entity);
	}

	@Override
	public void update(T entity) {
		em.merge(entity);
	}

	@Override
	public Optional<T> findOne(K id) {
		return Optional.ofNullable(em.find(clazz, id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<List<T>> findAll() {
		Query query = em.createQuery("SELECT t FROM " + getTypeClass().getName() + " t");
		return Optional.ofNullable(query.getResultList());
	}

	@Override
	public void delete(K id) throws ResourceNotFoundException {
		T entity = findOne(id).orElseThrow(() -> new ResourceNotFoundException());
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

	/**
	 * Método responsável por fazer o parse da Classe T recebida para o Objeto que utilizará a implementação.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 */
	@SuppressWarnings("unchecked")
	private Class<T> getTypeClass() {
		return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
	}

}
