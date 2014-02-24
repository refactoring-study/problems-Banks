package com.sns.domain;

import java.io.Serializable;
import java.util.Date;

import com.sns.core.sns.SocialConnection;
import com.sns.core.sns.SocialProvider;
import com.sns.core.sns.SocialServiceApi;
import com.sns.core.sns.facebook.FacebookApi;
import com.sns.core.sns.googleplus.GooglePlusApi;
import com.sns.core.sns.twitter.TwitterApi;
import com.sns.domain.exception.TwitterMessageTooLongException;

public class Post implements Serializable {

	private static final long serialVersionUID = 1L;

	private static final int TWITTER_MESSAGE_LIMIT = 140;

	private final String message;

	private final User owner;

	private final Date createdTime;

	public Post(String message, User owner) {
		this.message = message;
		this.owner = owner;
		this.createdTime = new Date();
	}

	public String getMessage() {
		return message;
	}

	public User getOwner() {
		return owner;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public void publish(UserSocialPostRepository repository) {
		for (SocialConnection connection : owner.getConnections()) {
			SocialServiceApi api = connection.getApi();
			SocialProvider provider = connection.getProvider();

			if (provider.equals(SocialProvider.FACEBOOK)) {
				// TODO save post to UserSocialPostRepository, or get Feed data from facebook
				((FacebookApi) api).updateStatus(message, null, null, null, null, null);
			} else if (provider.equals(SocialProvider.TWITTER)) {
				if (message.length() > TWITTER_MESSAGE_LIMIT) {
					throw new TwitterMessageTooLongException("message : " + message + " exceed twitter message length.");
				}
				// TODO save post to UserSocialPostRepository, or get Feed data from Twitter
				((TwitterApi) api).updateStatus(message);
			} else if (provider.equals(SocialProvider.GOOGLE_PLUS)) {
				// TODO save post to UserSocialPostRepository, or get Feed data from Google plus
				((GooglePlusApi) api).updateStatus(message);
			}
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((createdTime == null) ? 0 : createdTime.hashCode());
		result = prime * result + ((message == null) ? 0 : message.hashCode());
		result = prime * result + ((owner == null) ? 0 : owner.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		if (createdTime == null) {
			if (other.createdTime != null)
				return false;
		} else if (!createdTime.equals(other.createdTime))
			return false;
		if (message == null) {
			if (other.message != null)
				return false;
		} else if (!message.equals(other.message))
			return false;
		if (owner == null) {
			if (other.owner != null)
				return false;
		} else if (!owner.equals(other.owner))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Post [message=" + message + ", owner=" + owner + ", createdTime=" + createdTime + "]";
	}

}
