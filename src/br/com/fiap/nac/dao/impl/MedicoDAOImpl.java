package br.com.fiap.nac.dao.impl;

import javax.persistence.EntityManager;

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

}
