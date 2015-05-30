package com.zanox.rest.file.upload.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.zanox.rest.file.upload.model.UserProfile;
import com.zanox.rest.file.upload.service.UploadProfileService;
import com.zanox.rest.file.upload.validators.UploadFormValidator;

/**
 * Controller class to provide an endpoint for uploading files rest API.
 * 
 * @author Nagendra
 * 
 */
@Controller
public class UserProfileUploadController {

    private static final Logger LOGGER = Logger.getLogger(UserProfileUploadController.class.getName());
    
    @Autowired
    private UploadFormValidator uploadFormValidator;
    
    @Autowired
    private UploadProfileService uploadProfileService;
    
    @RequestMapping(method = RequestMethod.GET, value = "/upload")
    public String getUploadForm(Model model)
    {
      model.addAttribute(new UserProfile());
      return "upload/uploadForm";
    }
    
    @RequestMapping(method = RequestMethod.POST, value = "/upload/cv")
	public String uploadUserProfile(UserProfile userProfile, BindingResult result) {
    	uploadFormValidator.validate(userProfile, result);
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				LOGGER.error("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			return "upload/uploadForm";
		}

		LOGGER.info("Uploading user profile: " + userProfile);
		
		long userProfileId = uploadProfileService.uploadProfile(userProfile);
		
		LOGGER.debug("User profile ID: " + userProfileId);

		return "redirect:/rest/upload";
	}

}
