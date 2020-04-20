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
 * Classe respons�vel por aplicar as regras de neg�cio para {@link Medico}.
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
	 * M�todo respons�vel por inserir um novo {@link Medico} no banco de dados, verificando antes se o CRM informado est�
	 * dispon�vel para uso. Se o CRM j� tiver sido cadastrado para outro Medico, uma exce��o ser� lan�ada.
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
	 * M�todo respons�vel por buscar um {@link Medico} no banco de dados de acordo com o ID recebido por par�metro. Se nenhum
	 * registro for encontrado uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Medico findOne(final Long id) throws ResourceNotFoundException {
		return this.medicoDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("M�dico n�o encontrado"));
	}

	/**
	 * M�todo respons�vel por atualizar as informa��es de um {@link Medico} no banco de dados de acordo com os dados recebidos no
	 * objeto que est� sendo passado por par�metro, verificando antes se o CRM informado est� dispon�vel para uso. Se o CRM j�
	 * tiver sido cadastrado para outro Medico, uma exce��o ser� lan�ada.
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
	 * M�todo respons�vel por remover um {@link Medico} no banco de dados de acordo com o ID recebido por par�metro. Se o registro
	 * que ser� deletado n�o for encontrado uma exce��o ser� lan�ada.
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
	 * M�todo respons�vel por buscar todos os {@link Medico}'s existentes no banco de dados. Se nenhum registro for encontrado uma
	 * exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Medico> findAll() throws ResourceNotFoundException {
		return this.medicoDAO.findAll().orElseThrow(() -> new ResourceNotFoundException("Nenhum m�dico cadastrado"));
	}

	/**
	 * M�todo respons�vel por buscar uma lista de {@link Medico}'s de acordo com a area recebida por par�metro.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param id
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public List<Medico> findByArea(final AreaMedicinaEnum area) throws ResourceNotFoundException {
		return this.medicoDAO.findByArea(area.toString())
				.orElseThrow(() -> new ResourceNotFoundException("Nenhum m�dico especializado na area"));
	}

	/**
	 * M�todo respons�vel por validar todos os campos unique, para evitar erro de Constraint Violation no banco de dados. Se
	 * alguma chave estrangeira for violada uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param medico
	 * @return
	 * @throws UniqueConstraintViolationException
	 */
	private void validateUniqueFields(Medico medico) throws UniqueConstraintViolationException {
		StringBuilder critics = new StringBuilder();
		if (Optional.ofNullable(this.medicoDAO.findByCrm(medico.getCrm())).isPresent()) {
			critics.append(", o CRM informado j� est� em uso");
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
		MedicoService.EM.close();
	}

}
