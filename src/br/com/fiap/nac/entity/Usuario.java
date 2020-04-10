package br.com.fiap.nac.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.sun.istack.NotNull;

/**
 * Classe responsável por mapear a entidade USUARIO no banco de dados.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 20:59:55
 * @version 1.0
 */
@Entity
@Table(name = "USUARIOS")
@SequenceGenerator(name = "usuario", sequenceName = "USUARIO_SEQ", allocationSize = 1)
public class Usuario {

	/**
	 * Atributo id
	 */
	@Id
	@GeneratedValue(generator = "usuario", strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * Atributo endereco
	 */
	@NotNull
	@JoinColumn(name = "ID_ENDERECO", referencedColumnName = "id")
	private Endereco endereco;

	/**
	 * Atributo nome
	 */
	@NotNull
	@Column(length = 50)
	private String nome;

	/**
	 * Atributo ultimoNome
	 */
	@Column(length = 50)
	private String ultimoNome;

	/**
	 * Atributo usuario
	 */
	@NotNull
	@Column(length = 20)
	private String usuario;

	/**
	 * Atributo senha
	 */
	@NotNull
	@Column(length = 15)
	private String senha;

	/**
	 * Atributo dataCadastro
	 */
	@NotNull
	@Temporal(TemporalType.DATE)
	private Calendar dataCadastro;

	/**
	 * Construtor da classe Usuario
	 *
	 * @param id
	 * @param nome
	 * @param ultimoNome
	 * @param usuario
	 * @param senha
	 * @param dataCadastro
	 */
	public Usuario(String nome, String ultimoNome, String usuario, String senha, Calendar dataCadastro) {
		super();
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.usuario = usuario;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}

	/**
	 * Construtor da classe Usuario
	 */
	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUltimoNome() {
		return ultimoNome;
	}

	public void setUltimoNome(String ultimoNome) {
		this.ultimoNome = ultimoNome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Calendar getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Calendar dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

}
