package br.com.fiap.nac.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.nac.dao.EnderecoDAO;
import br.com.fiap.nac.dao.impl.CidadeDAOImpl;
import br.com.fiap.nac.dao.impl.EnderecoDAOImpl;
import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe main.
 *
 * @author Brazil Code - Andrew Pereira
 * @since 10 de abr de 2020 16:25:41
 * @version 1.0
 */
public class EnderecoConsoleView {

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

		Cidade cidade = new CidadeDAOImpl(em).findOne(1l).get();
		EnderecoDAO dao = new EnderecoDAOImpl(em);
		Endereco endereco = new Endereco("Capao", "05798100", "Casa2", "Rua gersom marques da silva", cidade);

		// Persist entity
		try {
			dao.save(endereco);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// Closing factory n entity manager
		em.close();
		factory.close();
	}

}
