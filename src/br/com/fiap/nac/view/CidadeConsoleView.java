package br.com.fiap.nac.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.nac.dao.CidadeDao;
import br.com.fiap.nac.dao.impl.CidadeDAOImpl;
import br.com.fiap.nac.dao.impl.EstadoDAOImpl;
import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe main.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 10 de abr de 2020 16:24:49
 * @version 1.0
 */
public class CidadeConsoleView {

	/**
	 * Método responsável por realizar o CRUD da entidade.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param args
	 */
	public static void main(String[] args) {
		// Initiate factory
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();

		CidadeDao dao = new CidadeDAOImpl(em);
		Estado estado = new EstadoDAOImpl(em).findOne(1l).get();
		Cidade cidade = new Cidade("Sao paulo", estado);

		// Persist entity
		try {
			dao.save(cidade);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Closing factory n entity manager
		em.close();
		factory.close();
	}

}
