package br.com.fiap.nac.view;

import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.nac.dao.CidadeDao;
import br.com.fiap.nac.dao.impl.CidadeDAOImpl;
import br.com.fiap.nac.dao.impl.EstadoDAOImpl;
import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

public class CidadeConsoleView {
	
	/**
	 * Método responsável por ...
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param args
	 */
	public static void main (String [] args) {
		
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();
		
		CidadeDao dao = new CidadeDAOImpl(em);
		Estado estado = new EstadoDAOImpl(em).findOne(1l).get();
		Cidade cidade = new Cidade("Sao paulo", estado );
		
		try {
			dao.save(cidade);
			dao.commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
		em.close();
		factory.close();
				
	}

}
