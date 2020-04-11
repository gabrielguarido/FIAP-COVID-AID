package br.com.fiap.nac.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe responsável por mapear a tabela MEDICOS no banco de dados.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:08:33
 * @version 1.0
 */
@Entity
@Table(name = "MEDICOS")
@SequenceGenerator(name = "medico", sequenceName = "MEDICO_SEQ", allocationSize = 1)
public class Medico {

	/**
	 * Atributo id
	 */
	@Id
	@GeneratedValue(generator = "medico", strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * Atributo usuario
	 */
	@JoinColumn(name = "ID_USUARIO", unique = true, nullable = false)
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Usuario usuario;

	/**
	 * Atributo crm
	 */
	@Column(length = 30, nullable = false, unique = true)
	private String crm;

	/**
	 * Atributo area
	 */
	@Column(length = 20, nullable = false)
	private String area;

	/**
	 * Construtor da classe Medico
	 *
	 * @param id
	 * @param usuario
	 * @param crm
	 * @param area
	 */
	public Medico(Usuario usuario, String crm, String area) {
		super();
		this.usuario = usuario;
		this.crm = crm;
		this.area = area;
	}

	/**
	 * Construtor da classe Medico
	 */
	public Medico() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public String getCrm() {
		return crm;
	}

	public void setCrm(String crm) {
		this.crm = crm;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	@Override
	public String toString() {
		return "Medico [id=" + id + ", usuario=" + usuario + ", crm=" + crm + ", area=" + area + "]";
	}

}
