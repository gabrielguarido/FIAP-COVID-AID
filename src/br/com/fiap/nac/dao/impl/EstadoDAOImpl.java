package br.com.fiap.nac.dao.impl;

import javax.persistence.EntityManager;

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

}
