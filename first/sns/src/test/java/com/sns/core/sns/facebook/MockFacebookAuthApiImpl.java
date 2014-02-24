package com.sns.core.sns.facebook;

import com.sns.core.sns.AccessToken;
import com.sns.core.sns.AuthParams;

public class MockFacebookAuthApiImpl implements FacebookAuthApi {

	@Override
	public String buildAuthorizeUrl(AuthParams params) {
		return "some-url";
	}

	@Override
	public AccessToken fetchAccessToken(String code) {
		return new AccessToken("value", "secret");
	}

}
