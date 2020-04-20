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
 * Classe respons�vel por aplicar as regras de neg�cio para {@link Usuario}.
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
	 * M�todo respons�vel por inserir um novo {@link Usuario} no banco de dados, verificando antes se o nome de usuario informado
	 * est� dispon�vel para uso. Se o nome de usu�rio j� tiver sido cadastrado para outro Usuario, uma exce��o ser� lan�ada.
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
	 * M�todo respons�vel por buscar um {@link Usuario} no banco de dados de acordo com o ID recebido por par�metro. Se nenhum
	 * registro for encontrado uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Usuario findOne(final Long id) throws ResourceNotFoundException {
		return this.usuarioDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Usu�rio n�o encontrado"));
	}

	/**
	 * M�todo respons�vel por atualizar as informa��es de um {@link Usuario} no banco de dados de acordo com os dados recebidos no
	 * objeto que est� sendo passado por par�metro, verificando antes se o nome de usuario informado est� dispon�vel para uso. Se
	 * o nome de usu�rio j� tiver sido cadastrado para outro Usuario, uma exce��o ser� lan�ada.
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
	 * M�todo respons�vel por remover um {@link Usuario} no banco de dados de acordo com o ID recebido por par�metro. Se o
	 * registro que ser� deletado n�o for encontrado uma exce��o ser� lan�ada.
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
	 * M�todo respons�vel por buscar todos os {@link Usuario}'s existentes no banco de dados. Se nenhum registro for encontrado
	 * uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Usuario> findAll() throws ResourceNotFoundException {
		return this.usuarioDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum usu�rio cadastrado"));
	}

	/**
	 * M�todo respons�vel por validar todos os campos unique, para evitar erro de Constraint Violation no banco de dados. Se
	 * alguma chave estrangeira for violada uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param usuario
	 * @return
	 * @throws UniqueConstraintViolationException
	 */
	private void validateUniqueFields(Usuario usuario) throws UniqueConstraintViolationException {
		StringBuilder critics = new StringBuilder();
		if (Optional.ofNullable(this.usuarioDAO.findByUsername(usuario.getUsuario())).isPresent()) {
			critics.append(", o nome de usuario informado j� est� em uso");
		}

		if (critics.length() > 1) {
			throw new UniqueConstraintViolationException("Cr�ticas" + critics.toString());
		}
	}

	/**
	 * M�todo respons�vel por fechar a instancia do EntityManager.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 */
	public void closeConnection() {
		UsuarioService.EM.close();
	}

}
