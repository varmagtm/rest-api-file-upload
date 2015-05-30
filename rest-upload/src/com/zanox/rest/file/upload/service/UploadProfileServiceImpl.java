/**
 * 
 */
package com.zanox.rest.file.upload.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zanox.rest.file.upload.dao.UserProfileDAO;
import com.zanox.rest.file.upload.model.UserProfile;

/**
 * Upload profile service implementaion.
 * 
 * @author Varma
 *
 */
@Service
public class UploadProfileServiceImpl implements UploadProfileService {
	
	@Autowired
	private UserProfileDAO userProfileDAO;

	@Override
	public long uploadProfile(UserProfile userProfile) {
		return userProfileDAO.saveUserProfile(userProfile);
	}

}
