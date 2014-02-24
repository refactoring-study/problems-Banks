package com.sns.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.sns.core.sns.SocialConnection;

public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String name;
	private String password;
	private List<SocialConnection> connections;
	private Date lastLoginTime;
	private Date createdTime;
	private Date lastModifiedTime;

	public User(String id, String name, PasswordEncoder encoder, String password) {
		this.id = id;
		this.name = name;
		this.password = encoder.encodePassword(password, this.id);
		this.connections = Lists.newArrayList();
		this.createdTime = new Date();
		this.lastModifiedTime = new Date();
	}

	public void addSocialConnection(final SocialConnection connection) {
		checkNotNull(connection, "connection");
		boolean isExistSameProviderConnection = Iterables.tryFind(this.connections, new Predicate<SocialConnection>() {
			public boolean apply(SocialConnection input) {
				if (connection.getProvider().equals(input.getProvider())) {
					return true;
				}
				return false;
			}
		}).isPresent();
		if (isExistSameProviderConnection) {
			throw new IllegalArgumentException("already has " + connection.getProvider() + " connection");
		}
		this.connections.add(connection);
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getPassword() {
		return password;
	}

	public List<SocialConnection> getConnections() {
		return ImmutableList.copyOf(this.connections);
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public Date getCreatedTime() {
		return createdTime;
	}

	public Date getLastModifiedTime() {
		return lastModifiedTime;
	}

	public boolean verifyCredentials(PasswordEncoder encoder, String rawPassword) {
		checkNotNull(encoder, "encoder");

		return encoder.isPasswordValid(password, rawPassword, id);
	}

	public void login(PasswordEncoder encoder, String rawPassword) throws AuthenticationException {
		if (!verifyCredentials(encoder, rawPassword)) {
			throw new AuthenticationException("password mismatch");
		}

		lastLoginTime = new Date();
	}

	public void changePassword(PasswordEncoder encoder, String rawOldPassword, String rawNewPassword)
			throws AuthenticationException {
		if (!verifyCredentials(encoder, rawOldPassword)) {
			throw new AuthenticationException("password mismatch");
		}

		password = encoder.encodePassword(rawNewPassword, id);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		User other = (User) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "User [id=" + id + "]";
	}

}
