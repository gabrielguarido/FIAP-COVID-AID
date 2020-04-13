package br.com.fiap.nac.dao;

import java.util.List;
import java.util.Optional;

import br.com.fiap.nac.entity.Medico;

/**
 * Classe responsável por implementar um repositório para {@link Medico}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:50:10
 * @version 1.0
 */
public interface MedicoDAO extends GenericDAO<Medico, Long> {

	/**
	 * Método responsável por buscar um {@link Medico} filtrando pelo CRM informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param crm
	 * @return
	 */
	Optional<Medico> findByCrm(final String crm);

	/**
	 * Método responsável por buscar uma lista de {@link Medico}'s filtrando pela AREA informada.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param area
	 * @return
	 */
	Optional<List<Medico>> findByArea(final String area);

}
