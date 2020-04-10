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

/**
 * Classe responsável por ...
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
	private int id;

	/**
	 * Atributo bairro
	 */
	private String bairro;
	/**
	 * Atributo cep
	 */
	private String cep;
	/**
	 * Atributo complemento
	 */
	private String complemento;
	/**
	 * Atributo logradouro
	 */
	private String logradouro;
	/**
	 * Atributo cidade
	 */
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_cidade", referencedColumnName = "id")
	private Cidade cidade;

	
	/**
	 * Construtor da classe Endereco
	 *
	 * @param id
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
	 *
	 */
	public Endereco() {
		super();
	}

	/**
	 * Método responsável por pegar o id
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	public int getId() {
		return id;
	}

	/**
	 * Método responsável por Inserir id
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Método responsável por Pegar Bairro
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * Método responsável por Inserir Bairro
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param bairro
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * Método responsável por buscar cep
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	public String getCep() {
		return cep;
	}

	/**
	 * Método responsável por inserir cep
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param cep
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * Método responsável por buscar complemento
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	public String getComplemento() {
		return complemento;
	}

	/**
	 * Método responsável por inserir complemento
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param complemento
	 */
	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	/**
	 * Método responsável por buscar logradouro
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * Método responsável por inserir Logradouro
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param logradouro
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * Método responsável por buscar cidade
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	public Cidade getCidade() {
		return cidade;
	}

	/**
	 * Método responsável por inserir cidade
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param cidade
	 */
	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}



}
