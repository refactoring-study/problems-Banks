package com.sns.core.sns.facebook;

import com.sns.core.sns.AccessToken;
import com.sns.core.sns.AuthParams;
import com.sns.core.sns.SocialAuthApi;

public interface FacebookAuthApi extends SocialAuthApi {
	String buildAuthorizeUrl(AuthParams params);

	AccessToken fetchAccessToken(String code);
}
