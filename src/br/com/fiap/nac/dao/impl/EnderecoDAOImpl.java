package br.com.fiap.nac.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EnderecoDao;
import br.com.fiap.nac.entity.Endereco;

/**
 * Classe responsável por implementar os métodos do repositório {@link EnderecoDAO}.
 *
 * @author Brazil Code - Andrew Pereira
 * @since Apr 10, 2020 4:14:42 PM
 * @version 1.0
 */
public class EnderecoDAOImpl extends GenericDAOImpl<Endereco, Long> implements EnderecoDao {

	/**
	 * Construtor da classe EnderecoDAOImpl
	 *
	 * @param em
	 */
	public EnderecoDAOImpl(EntityManager em) {
		super(em);
	}

}
