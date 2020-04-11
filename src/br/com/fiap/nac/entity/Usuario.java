package br.com.fiap.nac.entity;

import java.util.Calendar;

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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	@JoinColumn(name = "ID_ENDERECO", unique = false, nullable = false)
	@OneToOne(cascade = CascadeType.ALL)
	private Endereco endereco;

	/**
	 * Atributo nome
	 */
	@Column(length = 50, nullable = false)
	private String nome;

	/**
	 * Atributo ultimoNome
	 */
	@Column(length = 50)
	private String ultimoNome;

	/**
	 * Atributo usuario
	 */
	@Column(length = 20, nullable = false, unique = true)
	private String usuario;

	/**
	 * Atributo senha
	 */
	@Column(length = 15, nullable = false)
	private String senha;

	/**
	 * Atributo dataCadastro
	 */
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DATA_CADASTRO", nullable = false)
	private Calendar dataCadastro;

	/**
	 * Construtor da classe Usuario
	 *
	 * @param endereco
	 * @param nome
	 * @param ultimoNome
	 * @param usuario
	 * @param senha
	 * @param dataCadastro
	 */
	public Usuario(Endereco endereco, String nome, String ultimoNome, String usuario, String senha, Calendar dataCadastro) {
		super();
		this.endereco = endereco;
		this.nome = nome;
		this.ultimoNome = ultimoNome;
		this.usuario = usuario;
		this.senha = senha;
		this.dataCadastro = dataCadastro;
	}

	public Usuario() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", endereco=" + endereco + ", nome=" + nome + ", ultimoNome=" + ultimoNome + ", usuario="
				+ usuario + ", senha=" + senha + ", dataCadastro=" + dataCadastro + "]";
	}

}
