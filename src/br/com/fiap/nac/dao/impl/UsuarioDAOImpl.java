package br.com.fiap.nac.dao.impl;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.UsuarioDAO;
import br.com.fiap.nac.entity.Usuario;

/**
 * Classe responsável por implementar os métodos do repositório {@link UsuarioDAO}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:51:29
 * @version 1.0
 */
public class UsuarioDAOImpl extends GenericDAOImpl<Usuario, Long> implements UsuarioDAO {

	/**
	 * Construtor da classe UsuarioDAOImpl
	 *
	 * @param em
	 */
	public UsuarioDAOImpl(EntityManager em) {
		super(em);
	}

}
