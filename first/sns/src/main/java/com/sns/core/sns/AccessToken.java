package com.sns.core.sns;

import java.io.Serializable;

public class AccessToken implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final String value;
	private final String secret;

	public AccessToken(String value, String secret) {
		this.value = value;
		this.secret = secret;
	}

	public String getValue() {
		return value;
	}

	public String getSecret() {
		return secret;
	}
}
