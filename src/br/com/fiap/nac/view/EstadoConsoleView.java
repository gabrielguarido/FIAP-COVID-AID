package br.com.fiap.nac.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.nac.dao.EstadoDAO;
import br.com.fiap.nac.dao.impl.EstadoDAOImpl;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe main.
 *
 * @author Brazil Code - Gustavo Zotarelli
 * @since 10 de abr de 2020 15:51:03
 * @version 1.0
 */
public class EstadoConsoleView {

	/**
	 * Método responsável por realizar o CRUD da entidade.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param args
	 */
	public static void main(String[] args) {
		// Initiate factory
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();
		
		EstadoDAO estadoDAO = new EstadoDAOImpl(em);
		Estado estado = new Estado("São Paulo");
		
		// Persist entity
		try {
			estadoDAO.save(estado);
			estadoDAO.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Closing factory n entity manager
		em.close();
		factory.close();
	}

}
