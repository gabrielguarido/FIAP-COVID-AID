package br.com.fiap.nac.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.CidadeDAO;
import br.com.fiap.nac.dao.impl.CidadeDAOImpl;
import br.com.fiap.nac.entity.Cidade;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe responsável por aplicar as regras de negócio para {@link Cidade}.
 *
 * @author Brazil Code - Andrew Pereira
 * @since Apr 19, 2020 5:34:02 PM
 * @version 1.0
 */
public class CidadeService {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo cidadeDao
	 */
	private CidadeDAO cidadeDao = new CidadeDAOImpl(EM);

	/**
	 * Método responsável por buscar um {@link Cidade} no banco de dados de acordo com o ID recebido por parâmetro. Se nenhum
	 * registro for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Cidade findOne(final Long id) throws ResourceNotFoundException {
		return this.cidadeDao.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Cidade nï¿½o encontrado"));
	}

	/**
	 * Método responsável por atualizar as informações de um {@link Cidade} no banco de dados de acordo com os dados recebidos no
	 * objeto que está sendo passado por parâmetro, verificando antes se a cidade informada já foi cadastrada anteriormente.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param cidade
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void update(final Cidade cidade) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.validateUniqueFields(cidade);
		this.cidadeDao.update(cidade);
		this.cidadeDao.commit();
	}

	/**
	 * Método responsável por buscar todos os {@link Cidade}'s existentes no banco de dados. Se nenhum registro for encontrado uma
	 * exceção será lançada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Cidade> findAll() throws ResourceNotFoundException {
		return this.cidadeDao.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhuma cidade cadastrado"));
	}

	/**
	 * Método responsável por remover um {@link Cidade} no banco de dados de acordo com o ID recebido por parâmetro. Se o registro
	 * que será deletado não for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	public void delete(final Long id) throws ResourceNotFoundException, CommitException {
		this.cidadeDao.delete(id);
		this.cidadeDao.commit();
	}

	/**
	 * Método responsável por inserir um novo {@link Cidade} no banco de dados, verificando antes se a cidade informada já foi
	 * cadastrada anteriormente.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param cidade
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void save(final Cidade cidade) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.validateUniqueFields(cidade);
		this.cidadeDao.save(cidade);
		this.cidadeDao.commit();
	}

	/**
	 * Método responsável por validar todos os campos unique, para evitar erro de Constraint Violation no banco de dados. Se
	 * alguma chave estrangeira for violada uma exceção será lançada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param cidade
	 * @return
	 * @throws UniqueConstraintViolationException
	 */
	private void validateUniqueFields(Cidade cidade) throws UniqueConstraintViolationException {
		StringBuilder critics = new StringBuilder();
		if (Optional.ofNullable(this.cidadeDao.findByDescricao(cidade.getDescricao())).isPresent()) {
			critics.append(", Cidade já cadastrada!");
		}

		if (critics.length() > 1) {
			throw new UniqueConstraintViolationException("Criticas" + critics.toString());
		}
	}

	/**
	 * Método responsável por fechar a instancia do EntityManager.
	 *
	 * @author Brazil Code - Andrew Pereira
	 */
	public void closeConnection() {
		CidadeService.EM.close();
	}
}
