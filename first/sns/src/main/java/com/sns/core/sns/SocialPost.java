package com.sns.core.sns;

import java.util.Date;

public interface SocialPost {
	
	String getId();
	
	String getMessage();
	
	String getLink();
	
	String getName();
	
	String getCaption();
	
	String getDescription();
	
	String getPictureUrl();

	int getLikeCount();

	String getWriterName();

	String getWriterId();
	
	Date getCreatedTime();
	
}
