package br.com.fiap.nac.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.CidadeDao;
import br.com.fiap.nac.entity.Cidade;

/**
 * Classe responsável por implementar os métodos do repositório {@link CidadeDAO}.
 *
 * @author Brazil Code - Andrew Pereira
 * @since Apr 10, 2020 4:12:30 PM
 * @version 1.0
 */
public class CidadeDAOImpl extends GenericDAOImpl<Cidade, Long> implements CidadeDao {

	/**
	 * Construtor da classe CidadeDAOImpl
	 *
	 * @param em
	 */
	public CidadeDAOImpl(EntityManager em) {
		super(em);
	}

}
