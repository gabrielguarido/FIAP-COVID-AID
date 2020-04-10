package br.com.fiap.nac.view;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.nac.dao.UsuarioDAO;
import br.com.fiap.nac.dao.impl.UsuarioDAOImpl;
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe main.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 22:13:20
 * @version 1.0
 */
public class ConsoleView {

	/**
	 * Método responsável por realizar o CRUD das entidades.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param args
	 */
	public static void main(String[] args) {
		// Initiate factory
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();

		UsuarioDAO usuarioDAO = new UsuarioDAOImpl(em);
		Usuario usuario = new Usuario("Gabriel", "Oliveira", "gabriel", "teste", Calendar.getInstance());

		// Persist entity
		try {
			usuarioDAO.save(usuario);
			usuarioDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Closing factory n entity manager
		em.close();
		factory.close();
	}

}
