package com.sns.core.sns;

import com.sns.core.sns.facebook.FacebookAuthApi;
import com.sns.core.sns.facebook.MockFacebookApiImpl;
import com.sns.core.sns.facebook.MockFacebookAuthApiImpl;

public class FacebookClient extends SocialClient {

	public FacebookClient(String id, String name, String returnUri, String applicationKey, String applicationSecret) {
		super(id, name, returnUri, applicationKey, applicationSecret);
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
	public FacebookAuthApi getAuthApi() {
		return new MockFacebookAuthApiImpl();
	}

}
