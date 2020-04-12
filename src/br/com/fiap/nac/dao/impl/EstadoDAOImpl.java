package br.com.fiap.nac.dao.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.nac.dao.EstadoDAO;
import br.com.fiap.nac.entity.Estado;

/**
 * Classe responsável por implementar os métodos do repositório {@link EstadoDAO}.
 *
 * @author Brazil Code - Gustavo Zotarelli
 * @since 10 de abr de 2020 15:34:12
 * @version 1.0
 */
public class EstadoDAOImpl extends GenericDAOImpl<Estado, Long> implements EstadoDAO {

	/**
	 * Construtor da classe EstadoDAOImpl
	 *
	 * @param em
	 */
	public EstadoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Optional<Estado> findByDescricao(final String descricao) {
		TypedQuery<Estado> query = this.em.createQuery("SELECT e FROM Estado e WHERE e.descricao = :descricao", Estado.class);
		query.setParameter("descricao", descricao);

		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			return Optional.of(query.getResultList().get(0));
		}
	}

	@Override
	public Optional<Estado> findByUf(final String uf) {
		TypedQuery<Estado> query = this.em.createQuery("SELECT e FROM Estado e WHERE UPPER (e.uf) = UPPER(:uf)", Estado.class);
		query.setParameter("uf", uf);

		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			return Optional.of(query.getResultList().get(0));
		}
	}

}
