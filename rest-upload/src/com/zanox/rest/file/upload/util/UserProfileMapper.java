package com.zanox.rest.file.upload.util;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zanox.rest.file.upload.entities.UserProfileEntity;
import com.zanox.rest.file.upload.model.UserProfile;

/**
 * Mapper class for mapping user profile model object to entity and vice versa.
 * 
 * @author Varma
 *
 */
@Component
public class UserProfileMapper implements ModelEntityMapper<UserProfile, UserProfileEntity> {

	@Override
	public UserProfile getModel(UserProfileEntity e) {
		UserProfile userProfile = null;
		if (e != null) {
			userProfile = new UserProfile();
			userProfile.setEmail(e.getEmail());
			/*FileItem fileItem = new DiskFileItemFactory().;
			CommonsMultipartFile file = new CommonsMultipartFile(fileItem);*/
			// userProfile.setFile(e.getFile());
			userProfile.setFirstname(e.getFirstName());
			userProfile.setLastname(e.getLastName());
			userProfile.setJobtitle(e.getJobTitle());
			userProfile.setSource(e.getSource());
		}
		return userProfile;
	}

	@Override
	public UserProfileEntity getEntity(UserProfile m) {
		UserProfileEntity userProfileEntity = null;
		if (m != null) {
			userProfileEntity = new UserProfileEntity();
			userProfileEntity.setEmail(m.getEmail());
			userProfileEntity.setFirstName(m.getFirstname());
			userProfileEntity.setLastName(m.getLastname());
			userProfileEntity.setJobTitle(m.getJobtitle());
			userProfileEntity.setSource(m.getSource());
			CommonsMultipartFile file = m.getFile();
			if (file != null) {
				userProfileEntity.setFile(file.getBytes());
			}
		}
		return userProfileEntity;
	}

}
