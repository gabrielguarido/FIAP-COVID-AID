package br.com.fiap.nac.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.UsuarioDAO;
import br.com.fiap.nac.dao.impl.UsuarioDAOImpl;
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe responsável por aplicar as regras de negócio para {@link Usuario}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 18 de abr de 2020 17:13:20
 * @version 1.0
 */
public class UsuarioService {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo usuarioDAO
	 */
	private UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EM);

	/**
	 * Método responsável por inserir um novo {@link Usuario} no banco de dados, verificando antes se o nome de usuario informado
	 * está disponível para uso. Se o nome de usuário já tiver sido cadastrado para outro Usuario, uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param usuario
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void save(final Usuario usuario) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.validateUniqueFields(usuario);
		this.usuarioDAO.save(usuario);
		this.usuarioDAO.commit();
	}

	/**
	 * Método responsável por buscar um {@link Usuario} no banco de dados de acordo com o ID recebido por parâmetro. Se nenhum
	 * registro for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Usuario findOne(final Long id) throws ResourceNotFoundException {
		return this.usuarioDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
	}

	/**
	 * Método responsável por atualizar as informações de um {@link Usuario} no banco de dados de acordo com os dados recebidos no
	 * objeto que está sendo passado por parâmetro, verificando antes se o nome de usuario informado está disponível para uso. Se
	 * o nome de usuário já tiver sido cadastrado para outro Usuario, uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param usuario
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void update(final Usuario usuario) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.validateUniqueFields(usuario);
		this.usuarioDAO.update(usuario);
		this.usuarioDAO.commit();
	}

	/**
	 * Método responsável por remover um {@link Usuario} no banco de dados de acordo com o ID recebido por parâmetro. Se o
	 * registro que será deletado não for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	public void delete(final Long id) throws ResourceNotFoundException, CommitException {
		this.usuarioDAO.delete(id);
		this.usuarioDAO.commit();
	}

	/**
	 * Método responsável por buscar todos os {@link Usuario}'s existentes no banco de dados. Se nenhum registro for encontrado
	 * uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Usuario> findAll() throws ResourceNotFoundException {
		return this.usuarioDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário cadastrado"));
	}

	/**
	 * Método responsável por validar todos os campos unique, para evitar erro de Constraint Violation no banco de dados. Se
	 * alguma chave estrangeira for violada uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param usuario
	 * @return
	 * @throws UniqueConstraintViolationException
	 */
	private void validateUniqueFields(Usuario usuario) throws UniqueConstraintViolationException {
		StringBuilder critics = new StringBuilder();
		if (Optional.ofNullable(this.usuarioDAO.findByUsername(usuario.getUsuario())).isPresent()) {
			critics.append(", o nome de usuario informado já está em uso");
		}

		if (critics.length() > 1) {
			throw new UniqueConstraintViolationException("Críticas" + critics.toString());
		}
	}

	/**
	 * Método responsável por fechar a instancia do EntityManager.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 */
	public void closeConnection() {
		UsuarioService.EM.close();
	}

}
