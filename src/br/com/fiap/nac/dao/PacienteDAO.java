package br.com.fiap.nac.dao;

import java.util.List;
import java.util.Optional;

import br.com.fiap.nac.entity.Paciente;

/**
 * Classe respons�vel por implementar um reposit�rio para {@link Paciente}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:49:56
 * @version 1.0
 */
public interface PacienteDAO extends GenericDAO<Paciente, Long> {

	/**
	 * M�todo respons�vel por buscar um {@link Paciente} filtrando pelo CPF informado.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param cpf
	 * @return
	 */
	Optional<Paciente> findByCpf(final String cpf);

	/**
	 * M�todo respons�vel por buscar uma lista de {@link Paciente}'s filtrando pela m�dia de IDADES.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @param initialAge
	 * @param finalAge
	 * @return
	 */
	Optional<List<Paciente>> findByAgeAverage(final int initialAge, final int finalAge);

}
