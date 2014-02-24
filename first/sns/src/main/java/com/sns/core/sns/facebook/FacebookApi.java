package com.sns.core.sns.facebook;

import com.sns.core.sns.SocialPost;
import com.sns.core.sns.SocialPosts;
import com.sns.core.sns.SocialServiceApi;

public interface FacebookApi extends SocialServiceApi {
	void updateStatus(String message, String pictureUrl, String linkUrl, String name, String caption, String description);
	
	SocialPost getPost(String postId);
	
	SocialPosts getRecentPosts(int limit);
}
