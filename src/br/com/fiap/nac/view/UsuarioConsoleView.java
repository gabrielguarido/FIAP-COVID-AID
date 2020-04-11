package br.com.fiap.nac.view;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EnderecoDao;
import br.com.fiap.nac.dao.UsuarioDAO;
import br.com.fiap.nac.dao.impl.EnderecoDAOImpl;
import br.com.fiap.nac.dao.impl.UsuarioDAOImpl;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe main.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 22:13:20
 * @version 1.0
 */
public class UsuarioConsoleView {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo usuarioDAO
	 */
	private static UsuarioDAO usuarioDAO = new UsuarioDAOImpl(EM);

	/**
	 * Atributo enderecoDAO
	 */
	private static EnderecoDao enderecoDAO = new EnderecoDAOImpl(EM);

	/**
	 * Atributo ID_TESTE
	 */
	private static final Long ID_TESTE = 1L;

	/**
	 * Método responsável por chamar cada método de CRUD da entidade {@link Usuario}.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Buscando Endereco existente
			Endereco endereco = findAddress();

			// Criando e cadastrando novo Usuario
			Usuario usuario = new Usuario(endereco, "Teste", "Teste", "teste", "123", Calendar.getInstance());
			saveUser(usuario);

			// Buscando Usuario existente
			Usuario usuarioBD = findUser(usuario.getId());
			System.out.println(usuarioBD.toString());

			// Atualizando a senha do Usuario
			updateUser(usuarioBD);
			System.out.println("Senha atualizada: " + usuarioBD.getSenha());

			// Removendo Usuario
			deleteUser(usuarioBD.getId());

			// Buscando todos os Usuarios existentes
			List<Usuario> listaUsuarios = findAllUsers();
			listaUsuarios.forEach(us -> {
				System.out.println(us.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
	}

	/**
	 * Método responsável por buscar um {@link Endereco} no banco de dados de acordo com o ID recebido por parâmetro.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private static Endereco findAddress() throws ResourceNotFoundException {
		return enderecoDAO.findOne(ID_TESTE).orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
	}

	/**
	 * Método responsável por inserir um novo {@link Usuario} no banco de dados, verificando antes se o nome de usuario informado
	 * está disponível para uso.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param usuario
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	private static void saveUser(Usuario usuario) throws CommitException, UniqueConstraintViolationException {
		// Verificando se o nome de usuario já está sendo usado
		if (Optional.ofNullable(usuarioDAO.findByUsername(usuario.getUsuario())).isPresent()) {
			throw new UniqueConstraintViolationException("O nome de usuario informado já está em uso, escolha outro");
		} else {
			usuarioDAO.save(usuario);
			usuarioDAO.commit();
		}
	}

	/**
	 * Método responsável por buscar um {@link Usuario} no banco de dados de acordo com o ID recebido por parâmetro.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private static Usuario findUser(Long id) throws ResourceNotFoundException {
		return usuarioDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado"));
	}

	/**
	 * Método responsável por atualizar as informações de um {@link Usuario} no banco de dados de acordo com os dados recebidos no
	 * objeto que está sendo passado por parâmetro.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param usuario
	 * @throws CommitException
	 */
	private static void updateUser(Usuario usuario) throws CommitException {
		usuario.setSenha("456");
		usuarioDAO.update(usuario);
		usuarioDAO.commit();
	}

	/**
	 * Método responsável por remover um {@link Usuario} no banco de dados de acordo com o ID recebido por parâmetro.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	private static void deleteUser(Long id) throws ResourceNotFoundException, CommitException {
		usuarioDAO.delete(id);
		usuarioDAO.commit();
	}

	/**
	 * Método responsável por buscar todos os {@link Usuario}'s existentes no banco de dados.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private static List<Usuario> findAllUsers() throws ResourceNotFoundException {
		return usuarioDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum usuário cadastrado"));
	}

}
