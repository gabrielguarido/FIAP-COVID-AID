package br.com.fiap.nac.entity;

import javax.persistence.CascadeType;
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
 * Classe responsável por mapear a entidade MEDICO.
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
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "id")
	private Usuario usuario;

	/**
	 * Atributo crm
	 */
	@NotNull
	private String crm;

	/**
	 * Atributo area
	 */
	@NotNull
	private String area;

	/**
	 * Construtor da classe Medico
	 *
	 * @param id
	 * @param usuario
	 * @param crm
	 * @param area
	 */
	public Medico(Long id, Usuario usuario, String crm, String area) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.crm = crm;
		this.area = area;
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

}
