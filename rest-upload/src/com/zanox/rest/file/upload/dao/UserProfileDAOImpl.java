package com.zanox.rest.file.upload.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.zanox.rest.file.upload.entities.UserProfileEntity;
import com.zanox.rest.file.upload.model.UserProfile;
import com.zanox.rest.file.upload.util.UserProfileMapper;

@Repository
@Transactional
public class UserProfileDAOImpl implements UserProfileDAO {
	
	private static final Logger LOGGER = Logger.getLogger(UserProfileDAOImpl.class.getName());
	
	@Autowired	
	private SessionFactory sessionFactory;
	
	@Autowired
	private UserProfileMapper userProfileMapper;

	@Override
	public long saveUserProfile(UserProfile userProfile) {
		long userProfileId = -1;
		LOGGER.info("Persisting user profile: " + userProfile);
		UserProfileEntity entity = userProfileMapper.getEntity(userProfile);
		if (entity != null) {
			try {
				sessionFactory.getCurrentSession().save(entity);
				userProfileId = entity.getUserProfileId();
				LOGGER.info("User profile persisted successfully!");
				LOGGER.debug("User Profile ID: " + userProfileId);
			} catch (HibernateException e) {
				LOGGER.error("Error in persisting the user profile: " + e.getMessage(), e);
			}
		}
		return userProfileId;
	}

}
