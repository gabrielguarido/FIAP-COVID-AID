package br.com.fiap.nac.dao;

import java.util.Optional;

import br.com.fiap.nac.entity.Estado;

/**
 * Classe respons�vel por implementar um reposit�rio para {@link Estado}.
 *
 * @author Brazil Code - Gustavo Zotarelli
 * @since 10 de abr de 2020 15:31:16
 * @version 1.0
 */
public interface EstadoDAO extends GenericDAO<Estado, Long> {

	/**
	 * M�todo respons�vel por buscar um {@link Estado} filtrando pela DESCRICAO informada.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param descricao
	 * @return
	 */
	Optional<Estado> findByDescricao(final String descricao);

	/**
	 * M�todo respons�vel por buscar um {@link Estado} filtrando pela UF informada.
	 *
	 * @author Brazil Code - Gustavo Zotarelli
	 * @param uf
	 * @return
	 */
	Optional<Estado> findByUf(final String uf);

}
