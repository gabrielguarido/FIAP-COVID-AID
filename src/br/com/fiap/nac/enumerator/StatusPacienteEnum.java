package br.com.fiap.nac.enumerator;

import br.com.fiap.nac.entity.Paciente;

/**
 * Classe responsável por enumerar todos os status do {@link Paciente}.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:18:13
 * @version 1.0
 */
public enum StatusPacienteEnum {

	VERMELHO("INFECTADO"),
	LARANJA("SUSPEITA"),
	VERDE("NAO_INFECTADO");

	/**
	 * Atributo descricao
	 */
	private String descricao;

	/**
	 * Construtor da classe StatusPacienteEnum
	 *
	 * @param descricao
	 */
	private StatusPacienteEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
