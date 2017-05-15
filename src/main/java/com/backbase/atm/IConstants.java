package com.backbase.atm;

/**
 * This interface defines all application constants
 *
 */
public interface IConstants {

	/**
	 * Error code for city empty message
	 */
	public static final String INVALID_PARAM = "100";
	/**
	 * Error code for city empty message
	 */
	public static final String CITY_EMPTY_MSG = "101";
	/**
	 * Error code for post code empty message
	 */
	public static final String POST_EMPTY_MSG = "102";
	/**
	 * Error code for parsing error
	 */
	public static final String PARSING_ERROR = "103";
	/**
	 * Error code for JSON to JAVA mapping error
	 */
	public static final String MAPPING_ERROR = "104";
	/**
	 * Error code for IO error
	 */
	public static final String IO_ERROR = "105";
	/**
	 * Error code for application error
	 */
	public static final String APP_ERROR = "106";
	/**
	 * Regular expression to identify post code
	 */
	public static final String NUM_REGEX = "[0-9](.*)";

	/**
	 * JSON Array start elements
	 */
	public static final String JSON_ARRAY_ELEMENT = "[{";
	/**
	 * Constant for URI
	 */
	public static final String URI = "uri";
	/**
	 * Constant for Realm
	 */
	public static final String REALM = "ATM_REALM";
	/**
	 * Constant for WWW Auth
	 */
	public static final String WWWA = "WWW-Authenticate";
	/**
	 * Constant for basic realm
	 */
	public static final String BASIC = "Basic realm=";
	/**
	 * Constant for HTTP code 401
	 */
	public static final String ST_401 = "HTTP Status 401 : ";
	/**
	 * Constant for user name
	 */
	public static final String USER = "atmuser";
	/**
	 * Constant for password
	 */
	public static final String PWD = "ing123";
	/**
	 * Constant for Role
	 */
	public static final String ROLE = "ADMIN";

}
