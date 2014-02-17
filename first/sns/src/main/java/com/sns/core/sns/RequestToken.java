package com.sns.core.sns;

import java.io.Serializable;

public class RequestToken implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String value;
	private final String secret;

	public RequestToken(String value, String secret) {
		this.value = value;
		this.secret = secret;
	}

	public String getValue() {
		return value;
	}

	public String getSecret() {
		return secret;
	}

	@Override
	public String toString() {
		return "RequestToken [value=" + value + ", secret=" + secret + "]";
	}
	
}
