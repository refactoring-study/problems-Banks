package com.sns.core.sns.twitter;

import com.sns.core.sns.SocialPost;
import com.sns.core.sns.SocialPosts;
import com.sns.core.sns.SocialServiceApi;

public interface TwitterApi extends SocialServiceApi {
	SocialPost updateStatus(String status);

	SocialPost getPost(String postId);

	SocialPosts getRecentPosts(int limit);
}
