package com.backbase.atm;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.backbase.atm.config.AppConfig;
import com.backbase.atm.config.WebAppInitializer;
import com.backbase.atm.service.IAtmService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { AppConfig.class, WebAppInitializer.class })
@WebAppConfiguration
public class AtmControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext wac;

	@Autowired
	IAtmService atmService;

	@Autowired
	AtmResponseProcessor processor;

	public static final String CITY = "Amsterdam";
	public static final String UNKNOWN_CITY = "Unknown";
	public static final String POST_CODE = "3732 CM";
	public static final String UNKNOWN_POST = "9999 CM";

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void testByCity() throws Exception {
		final ResultActions actions = mockMvc.perform(get("/city/" + CITY).accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray());
		for (int i = 0; i < 118; i++) {
			actions.andExpect(jsonPath("$[" + i + "].address.city").value(CITY));
		}
	}

	@Test
	public void testByCityNoResults() throws Exception {
		final ResultActions actions = mockMvc.perform(get("/city/" + "unknown").accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void testByPostCode() throws Exception {
		final ResultActions actions = mockMvc
				.perform(get("/postcode/" + POST_CODE).accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].address.postalcode").value(POST_CODE));
	}

	@Test
	public void testByPostCodeNoResults() throws Exception {
		final ResultActions actions = mockMvc
				.perform(get("/postcode/" + UNKNOWN_POST).accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(0)));
	}

	@Test
	public void testSearchByCity() throws Exception {
		final ResultActions actions = mockMvc.perform(get("/search/" + CITY).accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray());
		for (int i = 0; i < 118; i++) {
			actions.andExpect(jsonPath("$[" + i + "].address.city").value(CITY));
		}
	}

	@Test
	public void testSearchByPostCode() throws Exception {
		final ResultActions actions = mockMvc
				.perform(get("/search/" + POST_CODE).accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$").isArray()).andExpect(jsonPath("$[0].address.postalcode").value(POST_CODE));
	}

	@Test
	public void testSearchNoResults() throws Exception {
		final ResultActions actions = mockMvc
				.perform(get("/search/" + "unknown").accept(MediaType.APPLICATION_JSON));
		actions.andExpect(status().isOk()).andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$", hasSize(0)));
	}

}
