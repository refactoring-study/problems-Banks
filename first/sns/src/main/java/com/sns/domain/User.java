package com.sns.domain;

import java.util.Date;
import java.util.List;

import com.sns.core.sns.SocialConnection;

public class User {

	private String id;
	private String password;
	private List<SocialConnection> connections;
	private Date createdTime;
	private Date lastModifiedTime;

}
