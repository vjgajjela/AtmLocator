package com.backbase.atm;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.backbase.atm.model.Atm;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * This class processes JSON response
 *
 */
@Component
public class AtmResponseProcessor {

	private static final Logger logger = Logger.getLogger(AtmResponseProcessor.class);

	/**
	 * @param response
	 * @param city
	 * @return
	 * @throws IOException
	 *
	 *             This method filters ATMs by city
	 */
	public List<Atm> filterResponseByCity(final String response, final String city) throws IOException {
		final List<Atm> atmList = new ObjectMapper().readValue(response, new TypeReference<List<Atm>>() {
		});
		final List<Atm> returnList = atmList.stream().filter(atm -> city.equalsIgnoreCase(atm.getAddress().getCity()))
				.collect(Collectors.toList());
		if (logger.isDebugEnabled()) {
			logger.debug("returnList -->" + returnList);
		}
		return returnList;

	}

	/**
	 * @param response
	 * @param postCode
	 * @return
	 * @throws IOException
	 *
	 *             This method filters ATMs by post code
	 */
	public List<Atm> filterResponseByPostCode(final String response, final String postCode) throws IOException {
		final List<Atm> atmList = new ObjectMapper().readValue(response, new TypeReference<List<Atm>>() {
		});
		final List<Atm> returnList = atmList.stream()
				.filter(atm -> postCode.equalsIgnoreCase(atm.getAddress().getPostalcode()))
				.collect(Collectors.toList());
		if (logger.isDebugEnabled()) {
			logger.debug("returnList -->" + returnList);
		}
		return returnList;

	}
}
