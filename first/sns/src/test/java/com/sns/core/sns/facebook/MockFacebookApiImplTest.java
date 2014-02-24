package com.sns.core.sns.facebook;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sns.core.sns.SocialPosts;

public class MockFacebookApiImplTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetRecentPosts() {
		MockFacebookApiImpl api = new MockFacebookApiImpl();
		SocialPosts posts = api.getRecentPosts(7);
		assertThat(posts.getPosts().size(), is(7));
	}

}
