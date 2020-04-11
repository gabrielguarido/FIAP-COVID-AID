package br.com.fiap.nac.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import br.com.fiap.nac.enumerator.StatusPacienteEnum;

/**
 * Classe responsável por mapear a tabela PACIENTES no banco de dados.
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
	@JoinColumn(name = "ID_USUARIO", unique = true, nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private Usuario usuario;

	/**
	 * Atributo cpf
	 */
	@Column(length = 15, nullable = false, unique = true)
	private String cpf;

	/**
	 * Atributo idade
	 */
	@Column(nullable = false)
	private int idade;

	/**
	 * Atributo status
	 */
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private StatusPacienteEnum status;

	/**
	 * Construtor da classe Paciente
	 *
	 * @param id
	 * @param usuario
	 * @param cpf
	 * @param idade
	 */
	public Paciente(Usuario usuario, String cpf, int idade) {
		super();
		this.usuario = usuario;
		this.cpf = cpf;
		this.idade = idade;
		this.status = StatusPacienteEnum.LARANJA;
	}

	/**
	 * Construtor da classe Paciente
	 */
	public Paciente() {

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

	@Override
	public String toString() {
		return "Paciente [id=" + id + ", usuario=" + usuario + ", cpf=" + cpf + ", idade=" + idade + ", status=" + status + "]";
	}

}
