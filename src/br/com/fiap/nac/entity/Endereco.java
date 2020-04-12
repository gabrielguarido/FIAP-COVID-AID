package br.com.fiap.nac.entity;

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
 * Classe responsável por mapear a tabela ENDERECOS no banco de dados.
 *
 * @author Brazil Code - Andrew Pereira
 * @since Apr 10, 2020 3:26:33 PM
 * @version 1.0
 */
@Entity
@Table(name = "ENDERECOS")
@SequenceGenerator(name = "endereco", allocationSize = 1)
public class Endereco {

	/**
	 * Atributo id
	 */
	@Id
	@GeneratedValue(generator = "endereco", strategy = GenerationType.SEQUENCE)
	private Long id;

	/**
	 * Atributo bairro
	 */
	@Column(length = 30, nullable = false)
	private String bairro;

	/**
	 * Atributo cep
	 */
	@Column(length = 10, nullable = false)
	private String cep;

	/**
	 * Atributo complemento
	 */
	@Column(length = 150)
	private String complemento;

	/**
	 * Atributo logradouro
	 */
	@Column(length = 100, nullable = false)
	private String logradouro;

	/**
	 * Atributo cidade
	 */
	@JoinColumn(name = "ID_CIDADE", unique = false, nullable = false)
	@OneToOne
	private Cidade cidade;

	/**
	 * Construtor da classe Endereco
	 *
	 * @param bairro
	 * @param cep
	 * @param complemento
	 * @param logradouro
	 * @param cidade
	 */
	public Endereco(String bairro, String cep, String complemento, String logradouro, Cidade cidade) {
		super();
		this.bairro = bairro;
		this.cep = cep;
		this.complemento = complemento;
		this.logradouro = logradouro;
		this.cidade = cidade;
	}

	/**
	 * Construtor da classe Endereco
	 */
	public Endereco() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	@Override
	public String toString() {
		return "Endereco [id=" + id + ", bairro=" + bairro + ", cep=" + cep + ", complemento=" + complemento + ", logradouro="
				+ logradouro + ", cidade=" + cidade + "]";
	}

}
