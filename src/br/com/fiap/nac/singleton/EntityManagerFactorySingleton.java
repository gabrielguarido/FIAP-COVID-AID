package br.com.fiap.nac.singleton;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Classe respons�vel por configurar a inst�ncia �nica de Entity Manager da aplica��o, aplicando Design Patttern de Singleton.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 20:57:02
 * @version 1.0
 */
public class EntityManagerFactorySingleton {

	/**
	 * Atributo factory
	 */
	private static EntityManagerFactory factory;

	/**
	 * Construtor da classe EntityManagerFactorySingleton
	 */
	private EntityManagerFactorySingleton() {

	}

	/**
	 * M�todo respons�vel por retornar uma inst�ncia est�tica do EntityManager.
	 *
	 * @author Brazil Code - Gabriel Guarido
	 * @return
	 */
	public static EntityManagerFactory getInstance() {
		if (factory == null)
			factory = Persistence.createEntityManagerFactory("oracle");

		return factory;
	}

}
