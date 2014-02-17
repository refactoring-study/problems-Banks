package com.sns.core.sns.twitter;

import com.sns.core.sns.AccessToken;
import com.sns.core.sns.RequestToken;
import com.sns.core.sns.SocialAuthApi;

public interface TwitterAuthApi extends SocialAuthApi {
	RequestToken fetchRequestToken();

	String buildAuthorizeUrl(RequestToken requestToken);

	AccessToken fetchAccessToken(RequestToken requestToken, String code);
}
