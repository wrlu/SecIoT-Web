package com.wrlus.seciot.traffic.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectionDetails {
	@JsonProperty("pair_hosts")
	private String[] pairIpAddress;
	@JsonProperty("pair_connection_protocol")
	private String[] pairConnectionProtocols;
	public String[] getPairIpAddress() {
		return pairIpAddress;
	}
	public void setPairIpAddress(String[] pairIpAddress) {
		this.pairIpAddress = pairIpAddress;
	}
	public String[] getPairConnectionProtocols() {
		return pairConnectionProtocols;
	}
	public void setPairConnectionProtocols(String[] pairConnectionProtocols) {
		this.pairConnectionProtocols = pairConnectionProtocols;
	}
	
}
