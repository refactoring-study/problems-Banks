package com.sns.core.sns.facebook;

import com.sns.core.sns.Post;
import com.sns.core.sns.Posts;
import com.sns.core.sns.SocialServiceApi;

public interface FacebookApi extends SocialServiceApi {
	void updateStatus(String message, String pictureUrl, String linkUrl, String name, String caption, String description);
	
	Post getPost(String postId);
	
	Posts getRecentPosts(int limit);
}
