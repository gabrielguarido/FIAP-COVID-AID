package br.com.fiap.nac.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.nac.dao.MedicoDAO;
import br.com.fiap.nac.entity.Medico;

/**
 * Classe responsável por implementar os métodos do repositório {@link MedicoDAO}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 10 de abr de 2020 15:15:20
 * @version 1.0
 */
public class MedicoDAOImpl extends GenericDAOImpl<Medico, Long> implements MedicoDAO {

	/**
	 * Construtor da classe MedicoDAOImpl
	 *
	 * @param em
	 */
	public MedicoDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Optional<Medico> findByCrm(final String crm) {
		TypedQuery<Medico> query = this.em.createQuery("SELECT m FROM Medico m WHERE LOWER(m.crm) LIKE LOWER(:crm)", Medico.class);
		query.setParameter("crm", crm);

		/**
		 * Mesmo que o atributo 'CRM' seja um campo unique no banco de dados, nesse caso não é possível utilizar o
		 * getSingleResult, pois ele irá retornar uma exceção caso nenhum registro seja encontrado, e não é o que precisamos.
		 * Portando para contornar o problema, utilizamos o getResultList e buscamos apenas o primeiro resultado.
		 */
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			return Optional.of(query.getResultList().get(0));
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public Optional<List<Medico>> findByArea(final String area) {
		Query query = em.createQuery("SELECT m FROM Medico m WHERE LOWER(m.area) LIKE LOWER(:area)");
		query.setParameter("area", area);
		return Optional.ofNullable(query.getResultList());
	}

}
