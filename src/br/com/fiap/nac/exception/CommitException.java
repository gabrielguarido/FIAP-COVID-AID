package br.com.fiap.nac.exception;

/**
 * Classe responsável por configurar uma exceção personalizada para Commits.
 *
 * @author Brazil Code - Gabriel Guarido
 * @since 9 de abr de 2020 21:29:26
 * @version 1.0
 */
public class CommitException extends Exception {

	/**
	 * Atributo serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Construtor da classe CommitException
	 */
	public CommitException() {
		super();
	}

	/**
	 * Construtor da classe CommitException
	 *
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public CommitException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	/**
	 * Construtor da classe CommitException
	 *
	 * @param message
	 * @param cause
	 */
	public CommitException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Construtor da classe CommitException
	 *
	 * @param message
	 */
	public CommitException(String message) {
		super(message);
	}

	/**
	 * Construtor da classe CommitException
	 *
	 * @param cause
	 */
	public CommitException(Throwable cause) {
		super(cause);
	}

}
