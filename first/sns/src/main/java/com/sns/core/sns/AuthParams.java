package com.sns.core.sns;

import java.util.Map;

import com.google.common.collect.Maps;

public class AuthParams {
	
	// permissions
	public static final String SCOPE = "scope";
	
	public static final String RESPONSE_TYPE = "response_type";
	
	public static final String REDIRECT_URI = "redirect_uri";
	
	public static final String STATE = "state";
	
	private Map<String, String> params;
	
	public AuthParams() {
		this.params = Maps.newHashMap();
	}
	
	public void set(String key, String value) {
		params.put(key,  value);
	}
	
	public String get(String key) {
		return params.get(key);
	}
	
	public String getScope() {
		return get(SCOPE);
	}
	
	public void setScope(String scope) {
		set(SCOPE, scope);
	}
	
	public boolean contains(String key) {
		return params.containsKey(key);
	}
	
}
