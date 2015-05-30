package com.zanox.rest.file.upload.dao;

import com.zanox.rest.file.upload.model.UserProfile;

public interface UserProfileDAO {
	
	/**
	 * Persists the user profile.
	 */
	public long saveUserProfile(UserProfile userProfile);

}
