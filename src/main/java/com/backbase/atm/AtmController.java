package com.backbase.atm;

import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.backbase.atm.exception.InvalidParamsException;
import com.backbase.atm.model.Atm;
import com.backbase.atm.service.IAtmService;

/**
 * This class is Rest controller and all API methods derived here
 *
 */
@RestController
public class AtmController {

	@Autowired
	IAtmService atmService;

	@Autowired
	AtmResponseProcessor processor;

	@Autowired
	private Environment env;

	private static final Logger logger = Logger.getLogger(AtmController.class);

	/**
	 * @param city
	 * @return
	 *
	 * 		This method returns ATMs by given city
	 * @throws Exception
	 */
	@RequestMapping(value = "/city/{city}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Atm>> getAtmByCity(@PathVariable final String city) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("city -->" + city);
		}
		if (StringUtils.isBlank(city)) {
			throw new InvalidParamsException(env.getProperty(IConstants.CITY_EMPTY_MSG));
		}
		return new ResponseEntity<List<Atm>>(processor.filterResponseByCity(atmService.getAtmsAsString(), city.trim()),
				HttpStatus.OK);
	}

	/**
	 * @param postcode
	 * @return
	 * @throws Exception
	 *
	 *             This method returns ATMs by post code
	 */
	@RequestMapping(value = "/postcode/{postcode}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Atm>> getAtmByPostCode(@PathVariable final String postcode) throws Exception {

		if (logger.isDebugEnabled()) {
			logger.debug("postCode -->" + postcode);
		}
		if (StringUtils.isBlank(postcode)) {
			throw new InvalidParamsException(env.getProperty(IConstants.POST_EMPTY_MSG));
		}
		return new ResponseEntity<List<Atm>>(
				processor.filterResponseByPostCode(atmService.getAtmsAsString(), postcode.trim()), HttpStatus.OK);
	}

	/**
	 * @param param
	 * @return
	 * @throws Exception
	 *             This method returns ATMs based on the input provided
	 */
	@RequestMapping(value = "/search/{param}", method = RequestMethod.GET, produces = "application/json")
	@ResponseBody
	public ResponseEntity<List<Atm>> getAtmList(@PathVariable final String param) throws Exception {
		final String input = param.trim();
		if (logger.isDebugEnabled()) {
			logger.debug("input -->" + input);
		}
		if (input.matches(IConstants.NUM_REGEX)) {
			return getAtmByPostCode(input);
		} else {
			return getAtmByCity(input);
		}
	}

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String welcomePage(final ModelMap model) {
		if (logger.isDebugEnabled()) {
			logger.debug("weclome page called");
		}
		return "atmlocator";
	}

}
