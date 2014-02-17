package com.sns.core.sns.twitter;

import com.sns.core.sns.Post;
import com.sns.core.sns.Posts;
import com.sns.core.sns.SocialServiceApi;

public interface TwitterApi extends SocialServiceApi {
	Post updateStatus(String status);

	Post getPost(String postId);

	Posts getRecentPosts(int limit);
}
