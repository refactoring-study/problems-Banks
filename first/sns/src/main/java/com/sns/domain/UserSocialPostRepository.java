package com.sns.domain;

import com.sns.core.sns.SocialPost;
import com.sns.core.sns.SocialPosts;

public interface UserSocialPostRepository {

	void save(User user, SocialPost post);

	SocialPost find(User user, String postId);

	SocialPosts findAll(User user);

	SocialPosts findAll(User user, Sort sort);
}
