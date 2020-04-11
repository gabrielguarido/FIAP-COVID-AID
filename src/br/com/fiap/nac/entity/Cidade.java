package br.com.fiap.nac.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe responsável por mapear a tabela CIDADES no banco de dados.
 *
 * @author Brazil Code - Andrew Pereira
 * @since Apr 10, 2020 3:33:10 PM
 * @version 1.0
 */

@Entity
@Table(name = "CIDADES")
@SequenceGenerator(name = "cidade", sequenceName = "CIDADE_SEQ", allocationSize = 1)
public class Cidade {

	/**
	 * Atributo id
	 */
	@Id
	@GeneratedValue(generator = "cidade", strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * Atributo descricao
	 */
	@Column(length = 30, nullable = false)
	private String descricao;

	/**
	 * Atributo estado
	 */
	@JoinColumn(name = "ID_ESTADO", unique = false, nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private Estado estado;

	/**
	 * Construtor da classe Cidade
	 *
	 * @param descricao
	 * @param estado
	 */
	public Cidade(String descricao, Estado estado) {
		super();
		this.descricao = descricao;
		this.estado = estado;
	}

	/**
	 * Construtor da classe Cidade
	 */
	public Cidade() {
		super();
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

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "Cidade [id=" + id + ", descricao=" + descricao + ", estado=" + estado + "]";
	}

}
