package br.com.fiap.nac.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EnderecoDAO;
import br.com.fiap.nac.dao.impl.EnderecoDAOImpl;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe respons�vel por aplicar as regras de neg�cio para {@link Endereco}.
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
	 * M�todo respons�vel por buscar um {@link Endereco} no banco de dados de acordo com o ID recebido por par�metro. Se nenhum
	 * registro for encontrado uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Endereco findOne(final Long id) throws ResourceNotFoundException {
		return this.enderecoDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Endereco n�o encontrado"));
	}

	/**
	 * M�todo respons�vel por atualizar as informa��es de um {@link Endereco} no banco de dados de acordo com os dados recebidos
	 * no objeto que est� sendo passado por par�metro.
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
	 * M�todo respons�vel por buscar todos os {@link Endereco}'s existentes no banco de dados. Se nenhum registro for encontrado
	 * uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Endereco> findAll() throws ResourceNotFoundException {
		return this.enderecoDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum endereco cadastrado"));
	}

	/**
	 * M�todo respons�vel por remover um {@link Endereco} no banco de dados de acordo com o ID recebido por par�metro. Se o
	 * registro que ser� deletado n�o for encontrado uma exce��o ser� lan�ada.
	 
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
	 * M�todo respons�vel por inserir um novo {@link Endereco} no banco de dados, verificando antes se o nome de usuario informado
	 * est� dispon�vel para uso. Se o nome de usu�rio j� tiver sido cadastrado para outro Usuario, uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param endereco
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void save(final Endereco endereco) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados

		this.enderecoDAO.save(endereco);
		this.enderecoDAO.commit();
	}

	/**
	 * M�todo respons�vel por fechar a instancia do EntityManager.
	 *
	 * @author Brazil Code - Andrew Pereira
	 */
	public void closeConnection() {
		EnderecoService.EM.close();
	}

}
