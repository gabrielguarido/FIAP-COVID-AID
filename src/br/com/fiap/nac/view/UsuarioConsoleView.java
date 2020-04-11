package br.com.fiap.nac.view;

import java.util.Calendar;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EnderecoDao;
import br.com.fiap.nac.dao.UsuarioDAO;
import br.com.fiap.nac.dao.impl.EnderecoDAOImpl;
import br.com.fiap.nac.dao.impl.UsuarioDAOImpl;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.entity.Usuario;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
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
			Usuario usuario = new Usuario(endereco, "Gabriel", "Oliveira", "gabriel", "123", Calendar.getInstance());
			saveUser(usuario);

			// Buscando Usuario existente
			System.out.println(findUser(ID_TESTE).toString());

			// Atualizando a senha do Usuario
			updateUser(usuario);
			System.out.println("Senha atualizada: " + findUser(ID_TESTE).getSenha());

			// Removendo Usuario
			deleteUser(ID_TESTE);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
	}

	/**
	 * Método responsável por buscar um {@link Endereco} qualquer no banco de dados para teste.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	private static Endereco findAddress() throws ResourceNotFoundException {
		return enderecoDAO.findOne(ID_TESTE).orElseThrow(() -> new ResourceNotFoundException("Endereco não encontrado"));
	}

	/**
	 * Método responsável por inserir um novo {@link Usuario} no banco de dados. 
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param usuario
	 * @throws CommitException
	 */
	private static void saveUser(Usuario usuario) throws CommitException {
		usuarioDAO.save(usuario);
		usuarioDAO.commit();
	}

	/**
	 * Método responsável por buscar um {@link Usuario} qualquer no banco de dados para teste.
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

	private static void deleteUser(Long id) throws ResourceNotFoundException {
		usuarioDAO.delete(id);
	}

}
