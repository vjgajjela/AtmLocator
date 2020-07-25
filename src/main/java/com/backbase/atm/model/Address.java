package com.backbase.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Bean for Address
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {

	/**
	 * Street
	 */
	private String street;

	/**
	 * House number
	 */
	private String housenumber;

	/**
	 * Postal code
	 */
	private String postalcode;

	/**
	 * City
	 */
	private String city;

	/**
	 * Geographical location
	 */
	private GeoLocation geoLocation;

	/**
	 * @return the street
	 */
	public String getStreet() {
		return street;
	}

	/**
	 * @param street
	 *            the street to set
	 */
	public void setStreet(final String street) {
		this.street = street;
	}

	/**
	 * @return the housenumber
	 */
	public String getHousenumber() {
		return housenumber;
	}

	/**
	 * @param housenumber
	 *            the housenumber to set
	 */
	public void setHousenumber(final String housenumber) {
		this.housenumber = housenumber;
	}

	/**
	 * @return the postalcode
	 */
	public String getPostalcode() {
		return postalcode;
	}

	/**
	 * @param postalcode
	 *            the postalcode to set
	 */
	public void setPostalcode(final String postalcode) {
		this.postalcode = postalcode;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city
	 *            the city to set
	 */
	public void setCity(final String city) {
		this.city = city;
	}

	/**
	 * @return the geoLocation
	 */
	public GeoLocation getGeoLocation() {
		return geoLocation;
	}

	/**
	 * @param geoLocation
	 *            the geoLocation to set
	 */
	public void setGeoLocation(final GeoLocation geoLocation) {
		this.geoLocation = geoLocation;
	}

	@Override
	public String toString() {
		return "Address{" + "street='" + street + '\'' + "houseNumber='" + housenumber + '\'' + "postalCode='"
				+ postalcode + '\'' + "city='" + city + '\'' + ", location=" + geoLocation + '}';
	}

}
