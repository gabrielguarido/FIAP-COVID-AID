package br.com.fiap.nac.exception;

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
