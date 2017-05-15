package com.backbase.atm;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.backbase.atm.config.AppConfig;
import com.backbase.atm.config.WebAppInitializer;
import com.backbase.atm.service.IAtmService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, WebAppInitializer.class })
@WebAppConfiguration
public class AtmResponseProcessorTest {

	@Autowired
	AtmResponseProcessor processor;

	@Autowired
	IAtmService service;

	public static final String CITY = "Amsterdam";
	public static final String UNKNOWN_CITY = "Unknown";
	public static final String POST_CODE = "3732 CM";
	public static final String UNKNOWN_POST = "9999 CM";
	public static final String INVALID_JOSON = ")]}',[{";

	@Test
	public void testFindByCity() throws Exception {
		assertEquals(true, processor.filterResponseByCity(service.getAtmsAsString(), CITY).size() > 0);
	}

	@Test
	public void testFindByCityNoResults() throws Exception {
		assertEquals(true, processor.filterResponseByCity(service.getAtmsAsString(), UNKNOWN_CITY).size() == 0);
	}

	@Test
	public void testFindByPost() throws Exception {
		assertEquals(true, processor.filterResponseByPostCode(service.getAtmsAsString(), POST_CODE).size() > 0);
	}

	@Test
	public void testFindByPostNoREsults() throws Exception {
		assertEquals(true, processor.filterResponseByPostCode(service.getAtmsAsString(), UNKNOWN_POST).size() == 0);
	}

	@Test(expected = JsonMappingException.class)
	public void testFindByCityMapping() throws Exception {
		assertEquals(true, processor.filterResponseByPostCode("", CITY).size() == 0);
	}

	@Test(expected = JsonMappingException.class)
	public void testFindByPostMapping() throws Exception {
		assertEquals(true, processor.filterResponseByPostCode("", UNKNOWN_POST).size() == 0);
	}

	@Test(expected = JsonParseException.class)
	public void testFindByCityParsing() throws Exception {
		assertEquals(true, processor.filterResponseByPostCode(INVALID_JOSON, CITY).size() == 0);
	}

	@Test(expected = JsonParseException.class)
	public void testFindByPostParsing() throws Exception {
		assertEquals(true, processor.filterResponseByPostCode(INVALID_JOSON, POST_CODE).size() == 0);
	}
}
