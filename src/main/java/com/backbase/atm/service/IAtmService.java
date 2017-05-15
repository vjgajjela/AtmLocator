package com.backbase.atm.service;

import java.io.IOException;
import java.util.List;

import com.backbase.atm.model.Atm;

/**
 * Interface for ATM service
 *
 */
public interface IAtmService {

	/**
	 * @return
	 * @throws IOException
	 *
	 *             Returns ATM response as list
	 */
	public List<Atm> getAtmsAsList() throws IOException;

	/**
	 * @return
	 * @throws IOException
	 *
	 *             Returns ATM response as list
	 */
	public String getAtmsAsString() throws IOException;
}
