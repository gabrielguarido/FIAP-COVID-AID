package br.com.fiap.nac.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * Classe responsável por ...
 *
 * @author Brazil Code - Andrew Pereira
 * @since Apr 10, 2020 3:33:10 PM
 * @version 1.0
 */

@Entity
@Table(name = "CIDADES")
@SequenceGenerator(name="cidade", sequenceName = "CIDADE_SEQ", allocationSize = 1)
public class Cidade{
	
	/**
	 * Atributo id
	 */
	@Id
	@GeneratedValue(generator = "cidade", strategy = GenerationType.SEQUENCE)
	private int id;
	/**
	 * Atributo descricao
	 */
	private String descricao;
	/**
	 * Atributo estado
	 */
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "id_estado", referencedColumnName = "id")
	private Estado estado;
	
	
	
	/**
	 * Construtor da classe Cidade
	 *
	 * @param id
	 * @param descricao
	 * @param estado
	 */
	public Cidade( String descricao, Estado estado) {
		super();
		this.descricao = descricao;
		this.estado = estado;
	}
	
	
	
	
	/**
	 * Construtor da classe Cidade
	 *
	 */
	public Cidade() {
		super();
	}




	/**
	 * Método responsável por buscar id
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	
	public int getId() {
		return id;
	}
	


	/**
	 * Método responsável por inserir id
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	
	/**
	 * Método responsável por buscar descricao
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	public String getDescricao() {
		return descricao;
	}
	
	/**
	 * Método responsável por inserir descricao
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param descricao
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	/**
	 * Método responsável por buscar estado
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @return
	 */
	public Estado getEstado() {
		return estado;
	}
	
	/**
	 * Método responsável por inserir estado
	 *
	 * @author Brazil Code - Andrew Pereira
	 * @param estado
	 */
	public void setEstado(Estado estado) {
		this.estado = estado;
	}
	

}
