package br.com.fiap.nac.enumerator;

public enum AreaMedicinaEnum {

	CARDIOLOGIA("Cardiologia"),
	CIRURGIA_GERAL("Cirurgia Geral"),
	CLINICA("Cl�nica"),
	EMERGENCIA("Emerg�ncia");

	/**
	 * Atributo descricao
	 */
	private String descricao;

	/**
	 * Construtor da classe AreaMedicinaEnum
	 *
	 * @param descricao
	 */
	private AreaMedicinaEnum(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

}
