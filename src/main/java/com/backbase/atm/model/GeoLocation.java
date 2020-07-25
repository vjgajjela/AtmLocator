package com.backbase.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * This bean holds geographical co-ordinates for given ATM
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeoLocation {

	/**
	 * Latitude
	 */
	private double lat;

	/**
	 * Longitude
	 */
	private double lng;

	/**
	 * @return the Latitude
	 */
	public double getLat() {
		return lat;
	}

	/**
	 * @param lat
	 *            the Latitude to set
	 */
	public void setLat(final double lat) {
		this.lat = lat;
	}

	/**
	 * @return the Longitude
	 */
	public double getLng() {
		return lng;
	}

	/**
	 * @param lng
	 *            the Longitude to set
	 */
	public void setLng(final double lng) {
		this.lng = lng;
	}

	@Override
	public String toString() {
		return "GeoLocation{" + "latitude='" + lat + '\'' + ", longitude=" + lng + '}';
	}

}
