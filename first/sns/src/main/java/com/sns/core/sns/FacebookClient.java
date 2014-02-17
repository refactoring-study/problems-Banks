package com.sns.core.sns;

import java.util.Date;

import com.sns.core.sns.facebook.MockFacebookApiImpl;
import com.sns.core.sns.facebook.MockFacebookAuthApiImpl;

public class FacebookClient extends SocialClient {

	public FacebookClient(String id, String name, String returnUri, String applicationKey, String applicationSecret,
			Date createdTime, Date lastModifiedTime) {
		super(id, name, returnUri, applicationKey, applicationSecret, createdTime, lastModifiedTime);
	}

	private static final long serialVersionUID = 1L;
	
	

	@Override
	public SocialProvider getProvider() {
		return SocialProvider.FACEBOOK;
	}

	@Override
	public SocialServiceApi getServiceApi(AccessToken accessToken) {
		return new MockFacebookApiImpl();
	}

	@Override
	public SocialAuthApi getAuthApi() {
		return new MockFacebookAuthApiImpl();
	}

}
