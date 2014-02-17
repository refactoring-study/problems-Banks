package com.sns.core.sns;

import java.util.List;

public class Posts {
	private final int total;
	private final List<Post> posts;
	
	public Posts(List<Post> posts) {
		this(posts, posts.size());
	}
	
	public Posts(List<Post> posts, int total) {
		this.total = total;
		this.posts = posts;
	}
	
	public int getTotal() {
		return total;
	}
	
	public List<Post> getPosts() {
		return posts;
	}
}
