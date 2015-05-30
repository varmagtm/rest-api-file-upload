package com.zanox.rest.file.upload.validators;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.zanox.rest.file.upload.model.UserProfile;

@Component(value = "uploadFormValidator")
public class UploadFormValidator implements Validator {
	
	private static final EmailValidator EMAIL_VALIDATOR = EmailValidator.getInstance();

	@Override
	public boolean supports(Class<?> clazz) {
		return UserProfile.class.equals(clazz);
	}
	
	@Override
	public void validate(Object arg0, Errors errors) {
		
		UserProfile uploadItem = (UserProfile) arg0;
		
		CommonsMultipartFile file = uploadItem.getFile();
		if (file == null || file.getOriginalFilename() == null || file.getOriginalFilename().trim().isEmpty()) {
			errors.rejectValue("file", "file.invalid");
		}
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "file", "file.empty", "Please upload CV");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstname", "firstname.empty", "First name cannot be null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastname", "lastname.empty", "Last name cannot be null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "email.empty", "Source cannot be null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "jobtitle", "jobtitle.empty", "Job title cannot be null");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "source", "source.empty", "Source cannot be null");
		
		if(!EMAIL_VALIDATOR.isValid(uploadItem.getEmail())) {
			errors.rejectValue("email", "email.invalid", "Invalid email");
		}
	}

}
