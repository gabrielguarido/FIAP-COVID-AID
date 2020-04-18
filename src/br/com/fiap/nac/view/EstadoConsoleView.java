package br.com.fiap.nac.view;

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
 * Classe main.
 *
 * @author Brazil Code - Gustavo Zotarelli
 * @since 10 de abr de 2020 15:51:03
 * @version 1.0
 */
public class EstadoConsoleView {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo estadoDAO
	 */
	private static EstadoDAO estadoDAO = new EstadoDAOImpl(EM);

	/**
	 * Método responsável por realizar o CRUD da entidade {@link Estado}.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			// Criando e cadastrando novo Estado
			Estado estado = new Estado("BrRaszília", "DF");
			saveState(estado);
			System.out.println("Estado cadastrado com sucesso");

			// Atualizando Estado cadastro
			updateState(estado);
			System.out.println("Estado atualizado: " + estado.getDescricao());

			// Removendo Estado
			deleteState(estado.getId());
			System.out.println("Estado excluído com sucesso!");

			// Buscando todos os Estados existentes
			List<Estado> listaEstados = findAllStates();
			listaEstados.forEach(es -> {
				System.out.println(es.toString());
			});
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			EM.close();
		}
	}

	/**
	 * Método responsável por inserri um novo {@link Estado} no banco de dados, veriricando se o estado e a uf já não se encontram
	 * cadastrados no sistema.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param estado
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	private static void saveState(Estado estado) throws CommitException, UniqueConstraintViolationException {
		// Verifica se o estado ou a uf já estão cadastrados.
		validateUniqueFields(estado);
		estadoDAO.save(estado);
		estadoDAO.commit();

	}

	/**
	 * Método responsável por atualizar as informações de um {@link Estado} no banco de dados de acordo com os dados recebidos no
	 * objeto que está sendo passado por parâmetro.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param estado
	 * @throws CommitException
	 */
	public static void updateState(Estado estado) throws CommitException {
		estado.setDescricao("Brasília");
		estadoDAO.update(estado);
		estadoDAO.commit();
	}

	/**
	 * Método responsável por remover um {@link Estado} no banco de dados de acordo com o ID recebido por parâmetro.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	public static void deleteState(Long id) throws ResourceNotFoundException, CommitException {
		estadoDAO.delete(id);
		estadoDAO.commit();
	}

	/**
	 * Método responsável por buscar todos os {@link Estado}'s existentes no banco de dados.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public static List<Estado> findAllStates() throws ResourceNotFoundException {
		return estadoDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Não existem estados cadastrados"));
	}

	/**
	 * Método responsável por validar todos os campos unique, para evitar erro de Constraint Violation no banco de dados. Se
	 * alguma chave estrangeira for violada uma exceção será lançada.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param estado
	 * @throws UniqueConstraintViolationException
	 */
	private static void validateUniqueFields(Estado estado) throws UniqueConstraintViolationException {
		StringBuilder critics = new StringBuilder();
		if (Optional.ofNullable(estadoDAO.findByDescricao(estado.getDescricao())).isPresent()) {
			critics.append(", o nome do Estado já se encontra cadastrado!");
		} else if (Optional.ofNullable(estadoDAO.findByUf(estado.getUf())).isPresent()) {
			critics.append(", a UF digitada já se encontra cadastrada!");
		}

		if (critics.length() > 1) {
			throw new UniqueConstraintViolationException("Críticas" + critics.toString());
		}
	}

}
