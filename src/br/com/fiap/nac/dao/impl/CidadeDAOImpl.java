package br.com.fiap.nac.dao.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.nac.dao.CidadeDAO;
import br.com.fiap.nac.entity.Cidade;

/**
 * Classe respons�vel por implementar os m�todos do reposit�rio {@link CidadeDAO}.
 *
 * @author Brazil Code - Andrew Pereira
 * @since Apr 10, 2020 4:12:30 PM
 * @version 1.0
 */
public class CidadeDAOImpl extends GenericDAOImpl<Cidade, Long> implements CidadeDAO {

	/**
	 * Construtor da classe CidadeDAOImpl
	 *
	 * @param em
	 */
	public CidadeDAOImpl(EntityManager em) {
		super(em);
	}

	public Optional<Cidade> findByDescricao(String descricao) {
		TypedQuery<Cidade> query = this.em.createQuery("SELECT e FROM Cidade e WHERE LOWER(e.descricao) LIKE LOWER(:descricao)", Cidade.class);
		query.setParameter("descricao", descricao);

		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			return Optional.of(query.getResultList().get(0));
		}
	}

}
