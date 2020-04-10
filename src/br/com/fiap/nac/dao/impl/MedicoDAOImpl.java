package br.com.fiap.nac.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.MedicoDAO;
import br.com.fiap.nac.entity.Medico;

public class MedicoDAOImpl extends GenericDAOImpl<Medico, Long> implements MedicoDAO {

	/**
	 * Construtor da classe MedicoDAOImpl
	 *
	 * @param em
	 */
	public MedicoDAOImpl(EntityManager em) {
		super(em);
	}

}
