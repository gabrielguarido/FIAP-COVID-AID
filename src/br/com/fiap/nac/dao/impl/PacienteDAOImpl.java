package br.com.fiap.nac.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.PacienteDAO;
import br.com.fiap.nac.entity.Paciente;

/**
 * Classe responsável por ...
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

}
