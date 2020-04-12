package br.com.fiap.nac.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe responsável por mapear a tabela ESTADOS no banco de dados.
 *
 * @author Brazil Code - Gustavo Zotarelli
 * @since 10 de abr de 2020 15:15:10
 * @version 1.0
 */
@Entity
@Table(name = "ESTADOS")
@SequenceGenerator(name = "estado", sequenceName = "ESTADO_SEQ", allocationSize = 1)
public class Estado {

	/**
	 * Atributo id
	 */
	@Id
	@GeneratedValue(generator = "estado", strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * Atributo descricao
	 */
	@Column(length = 50, nullable = false, unique = true)
	private String descricao;

	/**
	 * Atributo uf
	 */
	@Column(length = 2, nullable = false, unique = true)
	private String uf;

	/**
	 * Construtor da classe Estado
	 */
	public Estado() {
		super();
	}

	/**
	 * Construtor da classe Estado
	 *
	 * @param descricao
	 * @param uf
	 */
	public Estado(String descricao, String uf) {
		super();
		setDescricao(descricao);
		setUf(uf);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	@Override
	public String toString() {
		return "Estado [id=" + id + ", descricao=" + descricao + "]";
	}

}
