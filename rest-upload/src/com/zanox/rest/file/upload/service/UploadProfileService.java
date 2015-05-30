/**
 * 
 */
package com.zanox.rest.file.upload.service;

import com.zanox.rest.file.upload.model.UserProfile;

/**
 * Service interface for upload profile.
 * 
 * @author Varma
 *
 */
public interface UploadProfileService {
	
	/**
	 * Uploads user profile.
	 * @return the profile ID.
	 */
	public long uploadProfile(UserProfile userProfile);

}
