package sv.com.iblat.billing.backend.util;

/**
 * The Class AppServiceException.
 */
public class AppServiceException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	private final String code;

	/**
	 * Instantiates a new app service exception.
	 *
	 * @param code    the code
	 * @param message the message
	 */
	public AppServiceException(String code, String message) {
		super(message);
		this.code = code;
	}

	/**
	 * Instantiates a new app service exception.
	 *
	 * @param message the message
	 */
	public AppServiceException(String message) {
		super(message);
		this.code = "";
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
}
