package com.sns.core.sns.facebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.sns.core.sns.SocialPost;
import com.sns.core.sns.SocialPosts;

public class MockFacebookApiImpl implements FacebookApi {

	private final List<SocialPost> dumpPosts;

	public MockFacebookApiImpl() {
		dumpPosts = Lists.newArrayList();
		try {
			init();
		} catch (IOException | ParseException e) {
			throw new RuntimeException(e);
		}
	}

	private void init() throws IOException, ParseException {
		Charset charset = Charset.forName("UTF-8");
		Path path = Paths.get("src/test/resources/facebook-post-dump.txt");
		try (BufferedReader reader = Files.newBufferedReader(path, charset)) {
			String line = null;
			// read header & discard
			reader.readLine();
			// TODO refactoring
			while ((line = reader.readLine()) != null) {
				List<String> elements = Splitter.on(',').splitToList(line);
				String id = elements.get(0);
				String message = elements.get(1).trim();
				String messageType = elements.get(2).trim();
				int likeCount = Integer.valueOf(elements.get(3).trim());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/M/d HH:mm:ss");
				Date createdTime = sdf.parse(elements.get(4));
				String writerName = elements.get(5).trim();
				String writerId = elements.get(6).trim();
				dumpPosts.add(new FacebookPost(id, message, messageType, likeCount, createdTime, writerName, writerId));
			}
		}
		// reverse list for recent post is first.
		Lists.reverse(dumpPosts);
	}

	@Override
	public void updateStatus(String message, String pictureUrl, String linkUrl, String name, String caption,
			String description) {

	}

	@Override
	public SocialPost getPost(String postId) {
		for (SocialPost each : dumpPosts) {
			if (each.getId().equals(postId)) {
				return each;
			}
		}
		
		return null;
	}

	@Override
	public SocialPosts getRecentPosts(int limit) {
		return new SocialPosts(ImmutableList.copyOf(this.dumpPosts).subList(0, limit));
	}

	static class FacebookPost implements SocialPost {
		private String id;
		private String message;
		private String messageType;
		private int likeCount;
		private Date createdTime;
		private String writerName;
		private String writerId;

		public FacebookPost(String id, String message, String messageType, int likeCount, Date createdTime,
				String writerName, String writerId) {
			this.id = id;
			this.message = message;
			this.messageType = messageType;
			this.likeCount = likeCount;
			this.createdTime = createdTime;
			this.writerName = writerName;
			this.writerId = writerId;
		}

		@Override
		public String getId() {
			return id;
		}

		@Override
		public String getMessage() {
			return message;
		}

		@Override
		public String getLink() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getName() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getCaption() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getDescription() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String getPictureUrl() {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public int getLikeCount() {
			return likeCount;
		}

		@Override
		public String getWriterName() {
			return writerName;
		}

		@Override
		public String getWriterId() {
			return writerId;
		}

		@Override
		public Date getCreatedTime() {
			return createdTime;
		}

		@Override
		public String toString() {
			return "FacebookPost [id=" + id + ", message=" + message + ", messageType=" + messageType + ", likeCount="
					+ likeCount + ", createdTime=" + createdTime + ", writerName=" + writerName + ", writerId="
					+ writerId + "]";
		}

	}

}
