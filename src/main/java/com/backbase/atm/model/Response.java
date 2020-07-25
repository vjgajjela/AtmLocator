package com.backbase.atm.model;

/**
 * This bean is common bean for error responses send to client
 *
 */
public class Response {

	/**
	 * Error code or HTTP status code
	 */
	private String code;

	/**
	 * Error message
	 */
	private String message;

	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(final String code) {
		this.code = code;
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(final String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "Response{" + "code='" + code + '\'' + ", message=" + message + '}';
	}
}
