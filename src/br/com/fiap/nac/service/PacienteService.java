package br.com.fiap.nac.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.PacienteDAO;
import br.com.fiap.nac.dao.impl.PacienteDAOImpl;
import br.com.fiap.nac.entity.Paciente;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe responsável por aplicar as regras de negócio para {@link Paciente}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 19 de abr de 2020 10:39:57
 * @version 1.0
 */
public class PacienteService {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo pacienteDAO
	 */
	private PacienteDAO pacienteDAO = new PacienteDAOImpl(EM);

	/**
	 * Método responsável por inserir um novo {@link Paciente} no banco de dados, verificando antes se o CPF informado está
	 * disponível para uso. Se o CPF já tiver sido cadastrado para outro Paciente, uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param paciente
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void save(final Paciente paciente) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.validateUniqueFields(paciente);
		this.pacienteDAO.save(paciente);
		this.pacienteDAO.commit();
	}

	/**
	 * Método responsável por buscar um {@link Paciente} no banco de dados de acordo com o ID recebido por parâmetro. Se nenhum
	 * registro for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Paciente findOne(final Long id) throws ResourceNotFoundException {
		return this.pacienteDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Paciente não encontrado"));
	}

	/**
	 * Método responsável por atualizar as informações de um {@link Paciente} no banco de dados de acordo com os dados recebidos
	 * no objeto que está sendo passado por parâmetro, verificando antes se o CPF informado está disponível para uso. Se o CPF já
	 * tiver sido cadastrado para outro Paciente, uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param paciente
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void update(final Paciente paciente) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.validateUniqueFields(paciente);
		this.pacienteDAO.update(paciente);
		this.pacienteDAO.commit();
	}

	/**
	 * Método responsável por remover um {@link Paciente} no banco de dados de acordo com o ID recebido por parâmetro. Se o
	 * registro que será deletado não for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	public void delete(final Long id) throws ResourceNotFoundException, CommitException {
		this.pacienteDAO.delete(id);
		this.pacienteDAO.commit();
	}

	/**
	 * Método responsável por buscar todos os {@link Paciente}'s existentes no banco de dados. Se nenhum registro for encontrado
	 * uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Paciente> findAll() throws ResourceNotFoundException {
		return this.pacienteDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum paciente cadastrado"));
	}

	/**
	 * Método responsável por buscar todos os pacientes cadastrados, filtrando pelas idades informadas por parâmetro.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param idadeInicial
	 * @param idadeFial
	 * @return
	 */
	public List<Paciente> findByAgeAverage(final int idadeInicial, final int idadeFial) {
		return this.pacienteDAO.findByAgeAverage(idadeInicial, idadeFial).get();
	}

	/**
	 * Método responsável por buscar um {@link Paciente} no banco de dados pelo CPF informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param cpf
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Paciente findByCpf(final String cpf) throws ResourceNotFoundException {
		return pacienteDAO.findByCpf(cpf)
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum paciente cadastrado com este CPF"));
	}

	/**
	 * Método responsável por validar todos os campos unique, para evitar erro de Constraint Violation no banco de dados. Se
	 * alguma chave estrangeira for violada uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param paciente
	 * @return
	 * @throws UniqueConstraintViolationException
	 */
	private void validateUniqueFields(Paciente paciente) throws UniqueConstraintViolationException {
		StringBuilder critics = new StringBuilder();
		if (Optional.ofNullable(this.pacienteDAO.findByCpf(paciente.getCpf())).isPresent()) {
			critics.append(", o CPF informado já está em uso");
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
		PacienteService.EM.close();
	}

}
