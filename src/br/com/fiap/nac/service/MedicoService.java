package br.com.fiap.nac.service;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.MedicoDAO;
import br.com.fiap.nac.dao.impl.MedicoDAOImpl;
import br.com.fiap.nac.entity.Medico;
import br.com.fiap.nac.enumerator.AreaMedicinaEnum;
import br.com.fiap.nac.exception.CommitException;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.exception.UniqueConstraintViolationException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe responsável por aplicar as regras de negócio para {@link Medico}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 18 de abr de 2020 17:42:04
 * @version 1.0
 */
public class MedicoService {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo medicoDAO
	 */
	private MedicoDAO medicoDAO = new MedicoDAOImpl(EM);

	/**
	 * Método responsável por inserir um novo {@link Medico} no banco de dados, verificando antes se o CRM informado está
	 * disponível para uso. Se o CRM já tiver sido cadastrado para outro Medico, uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param medico
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void save(final Medico medico) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.validateUniqueFields(medico);
		this.medicoDAO.save(medico);
		this.medicoDAO.commit();
	}

	/**
	 * Método responsável por buscar um {@link Medico} no banco de dados de acordo com o ID recebido por parâmetro. Se nenhum
	 * registro for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Medico findOne(final Long id) throws ResourceNotFoundException {
		return this.medicoDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Médico não encontrado"));
	}

	/**
	 * Método responsável por atualizar as informações de um {@link Medico} no banco de dados de acordo com os dados recebidos no
	 * objeto que está sendo passado por parâmetro, verificando antes se o CRM informado está disponível para uso. Se o CRM já
	 * tiver sido cadastrado para outro Medico, uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param medico
	 * @throws CommitException
	 * @throws UniqueConstraintViolationException
	 */
	public void update(final Medico medico) throws CommitException, UniqueConstraintViolationException {
		// Validando os campos unique antes de tentar salvar no banco de dados
		this.validateUniqueFields(medico);
		this.medicoDAO.update(medico);
		this.medicoDAO.commit();
	}

	/**
	 * Método responsável por remover um {@link Medico} no banco de dados de acordo com o ID recebido por parâmetro. Se o registro
	 * que será deletado não for encontrado uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @throws ResourceNotFoundException
	 * @throws CommitException
	 */
	public void delete(final Long id) throws ResourceNotFoundException, CommitException {
		this.medicoDAO.delete(id);
		this.medicoDAO.commit();
	}

	/**
	 * Método responsável por buscar todos os {@link Medico}'s existentes no banco de dados. Se nenhum registro for encontrado uma
	 * exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Medico> findAll() throws ResourceNotFoundException {
		return this.medicoDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum médico cadastrado"));
	}

	/**
	 * Método responsável por buscar uma lista de {@link Medico}'s de acordo com a area recebida por parâmetro.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Medico> findByArea(final AreaMedicinaEnum area) throws ResourceNotFoundException {
		return this.medicoDAO.findByArea(area.toString())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum médico especializado na area"));
	}

	/**
	 * Método responsável por validar todos os campos unique, para evitar erro de Constraint Violation no banco de dados. Se
	 * alguma chave estrangeira for violada uma exceção será lançada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param medico
	 * @return
	 * @throws UniqueConstraintViolationException
	 */
	private void validateUniqueFields(Medico medico) throws UniqueConstraintViolationException {
		StringBuilder critics = new StringBuilder();
		if (Optional.ofNullable(this.medicoDAO.findByCrm(medico.getCrm())).isPresent()) {
			critics.append(", o CRM informado já está em uso");
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
		MedicoService.EM.close();
	}

}
