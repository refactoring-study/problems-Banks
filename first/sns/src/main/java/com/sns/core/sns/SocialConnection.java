package com.sns.core.sns;

import java.io.Serializable;
import java.util.Date;

import com.sns.domain.User;

public class SocialConnection implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

	private SocialProvider provider;

	private String accessToken;

	private String accessSecret;

	private User user;

	private SocialClient client;

	private Date createdTime;

	private Date lastModifiedTime;

	public SocialConnection(Long id, SocialProvider provider, String accessToken, String accessSecret, User user,
			SocialClient client) {
		super();
		this.id = id;
		this.provider = provider;
		this.accessToken = accessToken;
		this.accessSecret = accessSecret;
		this.user = user;
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

	public User getUser() {
		return user;
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
		result = prime * result + ((client == null) ? 0 : client.hashCode());
		result = prime * result + ((provider == null) ? 0 : provider.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
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
		if (client == null) {
			if (other.client != null)
				return false;
		} else if (!client.equals(other.client))
			return false;
		if (provider != other.provider)
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SocialConnection [id=" + id + ", provider=" + provider + ", accessToken=" + accessToken
				+ ", accessSecret=" + accessSecret + ", user=" + user + ", client=" + client + ", createdTime="
				+ createdTime + ", lastModifiedTime=" + lastModifiedTime + "]";
	}

}
