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
 * Classe responsável por mapear a entidade PACIENTE.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:16:05
 * @version 1.0
 */
@Entity
@Table(name = "PACIENTES")
@SequenceGenerator(name = "paciente", sequenceName = "PACIENTE_SEQ", allocationSize = 1)
public class Paciente {

	/**
	 * Atributo id
	 */
	@Id
	@GeneratedValue(generator = "paciente", strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * Atributo usuario
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "ID_USUARIO", referencedColumnName = "id")
	private Usuario usuario;

	/**
	 * Atributo cpf
	 */
	@NotNull
	private String cpf;

	/**
	 * Atributo idade
	 */
	@NotNull
	private int idade;

	/**
	 * Construtor da classe Paciente
	 *
	 * @param id
	 * @param usuario
	 * @param cpf
	 * @param idade
	 */
	public Paciente(Long id, Usuario usuario, String cpf, int idade) {
		super();
		this.id = id;
		this.usuario = usuario;
		this.cpf = cpf;
		this.idade = idade;
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public int getIdade() {
		return idade;
	}

	public void setIdade(int idade) {
		this.idade = idade;
	}

}
