package br.com.fiap.nac.view;

import java.util.Calendar;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import br.com.fiap.nac.dao.EnderecoDAO;
import br.com.fiap.nac.dao.impl.CidadeDAOImpl;
import br.com.fiap.nac.dao.impl.EnderecoDAOImpl;
import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.service.EnderecoService;
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
	 * M�todo respons�vel por realizar o CRUD da entidade.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param args
	 */
	public static void main(String[] args) {
		// Initiate factory
		EntityManagerFactory factory = EntityManagerFactorySingleton.getInstance();
		EntityManager em = factory.createEntityManager();

		//Buscando Cidade existente
		Cidade cidade = new CidadeDAOImpl(em).findOne(1l).get();
		EnderecoDAO dao = new EnderecoDAOImpl(em);
		
		// Criando e cadastrando novo ednereço
		Endereco endereco = new Endereco("Capao", "05798100", "Casa2", "Rua gersom marques da silva", cidade);

		// Persist entity
		try {
			dao.save(endereco);
			dao.commit();
			
			
			//instanciando service
			EnderecoService enderecoService = new EnderecoService();

			
			// Buscando endereco criado
			Endereco enderecoBD = enderecoService.findOne(endereco.getId());
			System.out.println(enderecoBD.toString());
			
			// Atualizando a endereco do Usuario criado
			enderecoBD.setBairro("bairro");
			enderecoBD.setCep("00000-000");
			enderecoBD.setComplemento("complemento");
			enderecoBD.setLogradouro("Logradouro");

			enderecoService.update(enderecoBD);
			System.out.println(enderecoBD.toString());
			
			// Removendo Endereços criado
			enderecoService.delete(enderecoBD.getId());
			

			// Buscando todos os Endereços existentes
			enderecoService.findAll().forEach(end -> {
				System.out.println(end.toString());
			});
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// Closing factory n entity manager
		em.close();
		factory.close();
	}

}


