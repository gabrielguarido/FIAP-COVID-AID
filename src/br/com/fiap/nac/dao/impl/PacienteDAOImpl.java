package br.com.fiap.nac.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import br.com.fiap.nac.dao.PacienteDAO;
import br.com.fiap.nac.entity.Paciente;

/**
 * Classe responsável por implementar os métodos do repositório {@link PacienteDAO}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:52:32
 * @version 1.0
 */
public class PacienteDAOImpl extends GenericDAOImpl<Paciente, Long> implements PacienteDAO {

	/**
	 * Construtor da classe PacienteDAOImpl
	 *
	 * @param em
	 */
	public PacienteDAOImpl(EntityManager em) {
		super(em);
	}

	@Override
	public Optional<Paciente> findByCpf(final String cpf) {
		TypedQuery<Paciente> query = this.em.createQuery("SELECT p FROM Paciente p WHERE LOWER(p.cpf) LIKE LOWER(:cpf)", Paciente.class);
		query.setParameter("cpf", cpf);

		/**
		 * Mesmo que o atributo 'CPF' seja um campo unique no banco de dados, nesse caso não é possível utilizar o
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
	public Optional<List<Paciente>> findByAgeAverage(final int initialAge, final int finalAge) {
		Query query = em.createQuery("SELECT p FROM Paciente p WHERE p.idade BETWEEN :begin AND :end");
		query.setParameter("begin", initialAge);
		query.setParameter("end", finalAge);
		return Optional.ofNullable(query.getResultList());
	}

}
