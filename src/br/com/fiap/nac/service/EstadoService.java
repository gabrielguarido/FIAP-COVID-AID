package br.com.fiap.nac.service;

import java.util.List;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EstadoDAO;
import br.com.fiap.nac.dao.impl.EstadoDAOImpl;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe responsável por ...
 *
 * @author Brazil Code - Andrew Pereira
 * @since Apr 19, 2020 7:33:13 PM
 * @version 1.0
 */
public class EstadoService {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo EstadoDao
	 */
	private EstadoDAO estadoDao = new EstadoDAOImpl(EM);

	/**
	 * M�todo respons�vel por inserir um novo {@link Estado} no banco de dados, verificando antes se o nome de usuario informado
	 * est� dispon�vel para uso. Se o nome de usu�rio j� tiver sido cadastrado para outro Usuario, uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param estado
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void save(final Estado estado) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.estadoDao.save(estado);
		this.estadoDao.commit();
	}

	/**
	 * M�todo respons�vel por buscar um {@link Estado} no banco de dados de acordo com o ID recebido por par�metro. Se nenhum
	 * registro for encontrado uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Estado findOne(final Long id) throws ResourceNotFoundException {
		return this.estadoDao.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Estado nao encontrado"));
	}

	/**
	 * M�todo respons�vel por atualizar as informa��es de um {@link Estado} no banco de dados de acordo com os dados recebidos no
	 * objeto que est� sendo passado por par�metro.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param estado
	 * @throws CommitException
	 */
	public void update(final Estado estado) throws CommitException {
		this.estadoDao.update(estado);
		this.estadoDao.commit();
	}

	/**
	 * M�todo respons�vel por remover um {@link Estado} no banco de dados de acordo com o ID recebido por par�metro. Se o registro
	 * que ser� deletado n�o for encontrado uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	public void delete(final Long id) throws ResourceNotFoundException, CommitException {
		this.estadoDao.delete(id);
		this.estadoDao.commit();
	}

	/**
	 * M�todo respons�vel por buscar todos os {@link Estado}'s existentes no banco de dados. Se nenhum registro for encontrado uma
	 * exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Estado> findAll() throws ResourceNotFoundException {
		return this.estadoDao.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum Estado cadastrado"));
	}

	/**
	 * M�todo respons�vel por fechar a instancia do EntityManager.
	 *
	 * @author Brazil Code - Andrew Pereira
	 */
	public void closeConnection() {
		EstadoService.EM.close();
	}

}
