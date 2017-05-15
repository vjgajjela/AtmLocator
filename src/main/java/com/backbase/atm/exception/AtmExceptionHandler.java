package com.backbase.atm.exception;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.backbase.atm.AtmController;
import com.backbase.atm.IConstants;
import com.backbase.atm.model.Response;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

/**
 * This class handles all exceptions in the application, return response
 * accordingly
 *
 */
@ControllerAdvice
public class AtmExceptionHandler {

	private static final Logger logger = Logger.getLogger(AtmController.class);

	/**
	 * @param exception
	 * @return
	 *
	 * 		This method creates error response
	 */
	@ExceptionHandler
	public ResponseEntity<Response> handleException(final Exception exception) {
		if (logger.isDebugEnabled()) {
			logger.debug("handing -->" + exception);
		}
		final Response errResponse = new Response();
		errResponse.setMessage(exception.getMessage());
		if (exception instanceof InvalidParamsException) {
			// creating response for Invalid parameters sent by client
			errResponse.setCode(IConstants.INVALID_PARAM);
			return new ResponseEntity<Response>(errResponse, HttpStatus.BAD_REQUEST);
		} else if (exception instanceof JsonParseException) {
			// creating response for parsing error
			errResponse.setCode(IConstants.PARSING_ERROR);
			return new ResponseEntity<Response>(errResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		} else if (exception instanceof JsonMappingException) {
			// creating response for mapping error
			errResponse.setCode(IConstants.MAPPING_ERROR);
			return new ResponseEntity<Response>(errResponse, HttpStatus.UNPROCESSABLE_ENTITY);
		} else if (exception instanceof IOException) {
			// creating response for IO error
			errResponse.setCode(IConstants.IO_ERROR);
			return new ResponseEntity<Response>(errResponse, HttpStatus.SERVICE_UNAVAILABLE);
		} else {
			// creating response for other exceptions
			errResponse.setCode(IConstants.APP_ERROR);
			return new ResponseEntity<Response>(errResponse, HttpStatus.BAD_GATEWAY);
		}
	}
}
