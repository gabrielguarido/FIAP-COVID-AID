package br.com.fiap.nac.exception;

/**
 * Classe responsável por configurar uma exceção personalizada para violação de chaves únicas.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 13 de abr de 2020 09:00:50
 * @version 1.0
 */
public class UniqueConstraintViolationException extends Exception {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe UniqueConstraintViolationException
	 */
	public UniqueConstraintViolationException() {
		super();
	}

	/**
	 * Construtor da classe UniqueConstraintViolationException
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public UniqueConstraintViolationException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Construtor da classe UniqueConstraintViolationException
	 *
	 * @param message
	 * @param cause
	 */
	public UniqueConstraintViolationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe UniqueConstraintViolationException
	 *
	 * @param message
	 */
	public UniqueConstraintViolationException(String message) {
		super(message);
	}

	/**
	 * Construtor da classe UniqueConstraintViolationException
	 *
	 * @param cause
	 */
	public UniqueConstraintViolationException(Throwable cause) {
		super(cause);
	}

}
