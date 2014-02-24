package com.sns.core.sns;

import java.util.List;

public class SocialPosts {
	private final int total;
	private final List<SocialPost> posts;
	
	public SocialPosts(List<SocialPost> posts) {
		this(posts, posts.size());
	}
	
	public SocialPosts(List<SocialPost> posts, int total) {
		this.total = total;
		this.posts = posts;
	}
	
	public int getTotal() {
		return total;
	}
	
	public List<SocialPost> getPosts() {
		return posts;
	}
}
