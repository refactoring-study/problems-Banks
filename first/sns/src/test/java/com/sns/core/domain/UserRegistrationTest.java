package com.sns.core.domain;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sns.core.sns.AccessToken;
import com.sns.core.sns.AuthParams;
import com.sns.core.sns.FacebookClient;
import com.sns.core.sns.SocialConnection;
import com.sns.core.sns.SocialProvider;
import com.sns.core.sns.facebook.FacebookAuthApi;
import com.sns.domain.PasswordEncoder;
import com.sns.domain.User;

public class UserRegistrationTest {
	PasswordEncoder mockPasswordEncoder;

	@Before
	public void setUp() throws Exception {
		mockPasswordEncoder = new MockPasswordEncoder();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreateUser() {
		User user = new User("id", "name", mockPasswordEncoder, "password");
		assertNotNull(user);
	}

	@Test
	public void testSocialConnectionAdd() {
		User user = new User("id", "name", mockPasswordEncoder, "password");
		FacebookClient client = new FacebookClient("id", "name", "returnUri", "appKey", "secret");
		FacebookAuthApi authApi = client.getAuthApi();
		@SuppressWarnings("unused")
		String url = authApi.buildAuthorizeUrl(new AuthParams());
		// send redirect & callback from facebook. return code value.
		AccessToken token = authApi.fetchAccessToken("code");
		SocialConnection facebookConnection = new SocialConnection(1L, SocialProvider.FACEBOOK, token.getValue(),
				token.getSecret(), client);
		
		user.addSocialConnection(facebookConnection);
	}

	static class MockPasswordEncoder implements PasswordEncoder {

		@Override
		public String encodePassword(String rawPass, Object salt) {
			return rawPass;
		}

		@Override
		public boolean isPasswordValid(String encPass, String rawPass, Object salt) {
			if (encPass.equals(rawPass)) {
				return false;
			}
			return true;
		}

	}

}
