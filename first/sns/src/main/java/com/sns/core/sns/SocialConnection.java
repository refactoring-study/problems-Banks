package com.sns.core.sns;

import java.io.Serializable;
import java.util.Date;

public class SocialConnection implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private SocialProvider provider;

	private String accessToken;

	private String accessSecret;

	private SocialClient client;

	private Date createdTime;

	private Date lastModifiedTime;

	public SocialConnection(Long id, SocialProvider provider, String accessToken, String accessSecret,
			SocialClient client) {
		super();
		this.id = id;
		this.provider = provider;
		this.accessToken = accessToken;
		this.accessSecret = accessSecret;
		this.client = client;
		this.createdTime = new Date();
		this.lastModifiedTime = new Date();
	}

	public SocialServiceApi getApi() {
		return client.getServiceApi(new AccessToken(accessToken, accessSecret));
	}

	public Long getId() {
		return id;
	}

	public SocialProvider getProvider() {
		return provider;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public String getAccessSecret() {
		return accessSecret;
	}

	public SocialClient getClient() {
		return client;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessSecret == null) ? 0 : accessSecret.hashCode());
		result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SocialConnection other = (SocialConnection) obj;
		if (accessSecret == null) {
			if (other.accessSecret != null)
				return false;
		} else if (!accessSecret.equals(other.accessSecret))
			return false;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (provider != other.provider)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SocialConnection [id=" + id + ", provider=" + provider + ", accessToken=" + accessToken
				+ ", accessSecret=" + accessSecret + ", client=" + client + ", createdTime=" + createdTime
				+ ", lastModifiedTime=" + lastModifiedTime + "]";
	}

}
