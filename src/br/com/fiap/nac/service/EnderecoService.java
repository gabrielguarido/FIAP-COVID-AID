package br.com.fiap.nac.service;

import javax.persistence.EntityManager;

import br.com.fiap.nac.dao.EnderecoDao;
import br.com.fiap.nac.dao.impl.EnderecoDAOImpl;
import br.com.fiap.nac.entity.Endereco;
import br.com.fiap.nac.exception.ResourceNotFoundException;
import br.com.fiap.nac.singleton.EntityManagerFactorySingleton;

/**
 * Classe respons�vel por aplicar as regras de neg�cio para {@link Endereco}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 18 de abr de 2020 17:22:42
 * @version 1.0
 */
public class EnderecoService {

	/**
	 * Atributo EM
	 */
	private static final EntityManager EM = EntityManagerFactorySingleton.getInstance().createEntityManager();

	/**
	 * Atributo enderecoDAO
	 */
	private EnderecoDao enderecoDAO = new EnderecoDAOImpl(EM);

	/**
	 * M�todo respons�vel por buscar um {@link Endereco} no banco de dados de acordo com o ID recebido por par�metro. Se nenhum
	 * registro for encontrado uma exce��o ser� lan�ada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 * @throws ResourceNotFoundException
	 */
	public Endereco findOne(final Long id) throws ResourceNotFoundException {
		return this.enderecoDAO.findOne(id).orElseThrow(() -> new ResourceNotFoundException("Endereco n�o encontrado"));
	}

}
