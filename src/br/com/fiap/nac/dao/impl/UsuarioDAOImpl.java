package br.com.fiap.nac.dao.impl;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.fiap.nac.dao.UsuarioDAO;
import br.com.fiap.nac.entity.Usuario;

/**
 * Classe respons�vel por implementar os m�todos do reposit�rio {@link UsuarioDAO}.
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

	@Override
	public Optional<Usuario> findByUsername(final String username) {
		TypedQuery<Usuario> query = this.em.createQuery("SELECT u FROM Usuario u WHERE LOWER(u.usuario) LIKE LOWER(:username)", Usuario.class);
		query.setParameter("username", username);

		/**
		 * Mesmo que o atributo 'usuario' seja um campo unique no banco de dados, nesse caso n�o � poss�vel utilizar o
		 * getSingleResult, pois ele ir� retornar uma exce��o caso nenhum registro seja encontrado, e n�o � o que precisamos.
		 * Portando para contornar o problema, utilizamos o getResultList e buscamos apenas o primeiro resultado.
		 */
		if (query.getResultList().isEmpty()) {
			return null;
		} else {
			return Optional.of(query.getResultList().get(0));
		}
	}

}
