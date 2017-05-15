package com.backbase.atm.service;

import java.io.IOException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.backbase.atm.IConstants;
import com.backbase.atm.model.Atm;

/**
 * This class calls ING API to get superset data of all ATM machines
 *
 */
@Component
public class AtmService implements IAtmService {

	private static final Logger logger = Logger.getLogger(AtmService.class);
	@Autowired
	private Environment env;

	/*
	 * This method returns response as ATM object list. It will works only if
	 * the JSON is in expected format else throws JsonParseException
	 */
	@Override
	public List<Atm> getAtmsAsList() throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("Getting ING list");
		}
		// calling ING service
		final RestTemplate restTemplate = new RestTemplate();
		final ResponseEntity<List<Atm>> list = restTemplate.exchange(env.getProperty(IConstants.URI), HttpMethod.GET,
				null, new ParameterizedTypeReference<List<Atm>>() {
				});
		return list.getBody();
	}

	/*
	 * This method returns response as String. The corrupted data can be removed
	 * through string manipulation.
	 */
	@Override
	public String getAtmsAsString() throws IOException {

		if (logger.isDebugEnabled()) {
			logger.debug("call to get list of ATMs");
		}
		// Calling ING service
		final String response = new RestTemplate().getForObject(env.getProperty(IConstants.URI), String.class);
		final int index = response.indexOf(IConstants.JSON_ARRAY_ELEMENT);
		if (index > 0) {
			return response.substring(index);
		}
		return response;
	}

}
