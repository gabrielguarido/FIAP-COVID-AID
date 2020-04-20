package br.com.fiap.nac.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EnderecoDAO;
import br.com.fiap.nac.dao.impl.EnderecoDAOImpl;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe responsável por aplicar as regras de negócio para {@link Endereco}.
 *
 * @author Brazil Code - Andrew Pereira
 * @since 18 de abr de 2020 17:22:42
 * @version 1.0
 */
public class EnderecoService {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo enderecoDAO
	 */
	private EnderecoDAO enderecoDAO = new EnderecoDAOImpl(EM);

	/**
	 * Método responsável por buscar um {@link Endereco} no banco de dados de acordo com o ID recebido por parâmetro. Se nenhum
	 * registro for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Endereco findOne(final Long id) throws ResourceNotFoundException {
		return this.enderecoDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
	}

	/**
	 * Método responsável por atualizar as informações de um {@link Endereco} no banco de dados de acordo com os dados recebidos
	 * no objeto que está sendo passado por parâmetro.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param endereo
	 * @throws CommitException
	 */
	public void update(final Endereco endereco) throws CommitException {
		this.enderecoDAO.update(endereco);
		this.enderecoDAO.commit();
	}

	/**
	 * Método responsável por buscar todos os {@link Endereco}'s existentes no banco de dados. Se nenhum registro for encontrado
	 * uma exceção será lançada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Endereco> findAll() throws ResourceNotFoundException {
		return this.enderecoDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum endereco cadastrado"));
	}

	/**
	 * Método responsável por remover um {@link Endereco} no banco de dados de acordo com o ID recebido por parâmetro. Se o
	 * registro que será deletado não for encontrado uma exceção será lançada.
	 * 
	 * @author Brazil Code - Andrew Pereira
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	public void delete(final Long id) throws ResourceNotFoundException, CommitException {
		this.enderecoDAO.delete(id);
		this.enderecoDAO.commit();
	}

	/**
	 * Método responsável por inserir um novo {@link Endereco} no banco de dados.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param endereco
	 * @throws CommitException
	 */
	public void save(final Endereco endereco) throws CommitException {
		this.enderecoDAO.save(endereco);
		this.enderecoDAO.commit();
	}

	/**
	 * Método responsável por fechar a instancia do EntityManager.
	 *
	 * @author Brazil Code - Andrew Pereira
	 */
	public void closeConnection() {
		EnderecoService.EM.close();
	}

}
