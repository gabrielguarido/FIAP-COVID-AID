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

import com.sun.istack.NotNull;

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
	private int id;

	/**
	 * Atributo descricao
	 */
	@NotNull
	@Column(length = 30)
	private String descricao;

	/**
	 * Atributo estado
	 */
	@NotNull
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado", referencedColumnName = "id")
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

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}
