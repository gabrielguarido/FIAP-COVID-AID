package br.com.fiap.nac.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EstadoDAO;
import br.com.fiap.nac.dao.impl.EstadoDAOImpl;
import br.com.fiap.nac.entity.Estado;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe respons�vel por aplicar as regras de neg�cio para {@link Estado}.
 *
 * @author Brazil Code - Gustavo Zotarelli
 * @since 19 de abr de 2020 22:08:45
 * @version 1.0
 */
public class EstadoService {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo estadoDAO
	 */
	private EstadoDAO estadoDAO = new EstadoDAOImpl(EM);

	/**
	 * M�todo respons�vel por inserri um novo {@link Estado} no banco de dados, veriricando se o estado e a uf j� n�o se encontram
	 * cadastrados no sistema.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param estado
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void save(final Estado estado) throws CommitException, UniqueConstraintViolationException {
		// Verifica se o Estado ou a UF j� est�o cadastrados.
		this.validateUniqueFields(estado);
		this.estadoDAO.save(estado);
		this.estadoDAO.commit();
	}

	/**
	 * M�todo respons�vel por busacar um {@link Estado} no banco de dados pelo ID informado por par�metro.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Estado findOne(final Long id) throws ResourceNotFoundException {
		return this.estadoDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Estado n�o encontrado"));
	}

	/**
	 * M�todo respons�vel por atualizar as informa��es de um {@link Estado} no banco de dados de acordo com os dados recebidos no
	 * objeto que est� sendo passado por par�metro.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param estado
	 * @throws CommitException
	 */
	public void update(Estado estado) throws CommitException {
		this.estadoDAO.update(estado);
		this.estadoDAO.commit();
	}

	/**
	 * M�todo respons�vel por remover um {@link Estado} no banco de dados de acordo com o ID recebido por par�metro.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	public void delete(final Long id) throws ResourceNotFoundException, CommitException {
		this.estadoDAO.delete(id);
		this.estadoDAO.commit();
	}

	/**
	 * M�todo respons�vel por buscar todos os {@link Estado}'s existentes no banco de dados.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Estado> findAll() throws ResourceNotFoundException {
		return this.estadoDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("N�o existem estados cadastrados"));
	}

	/**
	 * M�todo respons�vel por validar todos os campos unique, para evitar erro de Constraint Violation no banco de dados. Se
	 * alguma chave estrangeira for violada uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param estado
	 * @throws UniqueConstraintViolationException
	 */
	private void validateUniqueFields(Estado estado) throws UniqueConstraintViolationException {
		StringBuilder critics = new StringBuilder();
		if (Optional.ofNullable(this.estadoDAO.findByDescricao(estado.getDescricao())).isPresent()) {
			critics.append(", o nome do Estado j� se encontra cadastrado!");
		} else if (Optional.ofNullable(this.estadoDAO.findByUf(estado.getUf())).isPresent()) {
			critics.append(", a UF digitada j� se encontra cadastrada!");
		}

		if (critics.length() > 1) {
			throw new UniqueConstraintViolationException("Cr�ticas" + critics.toString());
		}

	}

	/**
	 * M�todo respons�vel por fechar a inst�ncia do EntityManager.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 */
	public void closeConnection() {
		EstadoService.EM.close();
	}

}
