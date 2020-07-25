package com.backbase.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This bean represents a single atm record of atm list provided.
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Atm {

	/**
	 * Address
	 */
	private Address address;

	/**
	 * Distance
	 */
	private String distance;

	/**
	 * Type
	 */
	private String type;

	/**
	 * @return the address
	 */
	public Address getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(final Address address) {
		this.address = address;
	}

	/**
	 * @return the distance
	 */
	public String getDistance() {
		return distance;
	}

	/**
	 * @param distance
	 *            the distance to set
	 */
	public void setDistance(final String distance) {
		this.distance = distance;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(final String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Atm{" + "address='" + address + '\'' + "distance='" + distance + '\'' + ", type=" + type + '}';
	}

}
