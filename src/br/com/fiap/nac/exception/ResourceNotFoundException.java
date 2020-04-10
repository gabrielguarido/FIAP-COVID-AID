package br.com.fiap.nac.exception;

/**
 * Classe responsável por configurar uma exceção personalizada para Recursos não Encontrados.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:28:58
 * @version 1.0
 */
public class ResourceNotFoundException extends Exception {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe ResourceNotFoundException
	 */
	public ResourceNotFoundException() {
		super();
	}

	/**
	 * Construtor da classe ResourceNotFoundException
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ResourceNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Construtor da classe ResourceNotFoundException
	 *
	 * @param message
	 * @param cause
	 */
	public ResourceNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe ResourceNotFoundException
	 *
	 * @param message
	 */
	public ResourceNotFoundException(String message) {
		super(message);
	}

	/**
	 * Construtor da classe ResourceNotFoundException
	 *
	 * @param cause
	 */
	public ResourceNotFoundException(Throwable cause) {
		super(cause);
	}

}
