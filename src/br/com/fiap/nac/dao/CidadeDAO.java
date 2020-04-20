package br.com.fiap.nac.dao;

import java.util.Optional;

import br.com.fiap.nac.entity.Cidade;

/**
 * Classe responsável por implementar um repositório para {@link Cidade}.
 *
 * @author Brazil Code - Andrew Pereira
 * @since 10 de abr de 2020 16:21:08
 * @version 1.0
 */
public interface CidadeDAO extends GenericDAO<Cidade, Long> {

	/**
	 * Método responsável por buscar um {@link Cidade} filtrando pela DESCRICAO informada.
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param descricao
	 * @return
	 */
	Optional<Cidade> findByDescricao(final String descricao);

}
