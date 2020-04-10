package br.com.fiap.nac.view;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.nac.dao.EnderecoDao;
import br.com.fiap.nac.dao.impl.CidadeDAOImpl;
import br.com.fiap.nac.dao.impl.EnderecoDAOImpl;
import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

public class EnredecoConsoleView {

public static void main(String[] args) {
	
	
	EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
	EntityManager em = factory.createEntityManager();
	
	Cidade cidade = new CidadeDAOImpl(em).findOne(1l).get();
	EnderecoDao dao = new EnderecoDAOImpl(em);
	
	Endereco endereco = new Endereco("Capao", "05798100", "Casa2", "Rua gersom marques da silva", cidade);
	
	try {
		dao.save(endereco);
		dao.commit();
	} catch (Exception e) {
		e.printStackTrace();
	}
	
	
}

}
