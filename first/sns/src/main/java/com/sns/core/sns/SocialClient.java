package com.sns.core.sns;

import java.io.Serializable;
import java.util.Date;

public abstract class SocialClient implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String returnUri;
	private String applicationKey;
	private String applicationSecret;
	private Date createdTime;
	private Date lastModifiedTime;

	public SocialClient(String id, String name, String returnUri, String applicationKey, String applicationSecret) {
		this.id = id;
		this.name = name;
		this.returnUri = returnUri;
		this.applicationKey = applicationKey;
		this.applicationSecret = applicationSecret;
		this.createdTime = new Date();
		this.lastModifiedTime = new Date();
	}

	public abstract SocialProvider getProvider();

	public abstract SocialServiceApi getServiceApi(AccessToken accessToken);

	public abstract SocialAuthApi getAuthApi();

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getReturnUri() {
		return returnUri;
	}

	public String getApplicationKey() {
		return applicationKey;
	}

	public String getApplicationSecret() {
		return applicationSecret;
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
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		SocialClient other = (SocialClient) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SocialClient [id=" + id + "]";
	}

}
