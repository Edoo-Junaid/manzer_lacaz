/*
 * Copyright (C) Acensi Group
 * 
 * All Rights Reserved Unauthorized copying of this file,
 * via any medium is strictly prohibited.
 * Proprietary and confidential.
 *
 */
package group.acensi.dafi.web.controller;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.http.HttpStatus;
import org.springframework.orm.jpa.JpaSystemException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import lombok.extern.slf4j.Slf4j;

/**
 *
 *
 * @author Nadeem Nayeck <nadeem.nayeck@acensi.group>
 *
 */
@ControllerAdvice
@Slf4j
public class ExceptionAdvice {
	
	
	@ResponseBody
	@ExceptionHandler(JpaSystemException.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String jpaSystemExceptionHandler(JpaSystemException ex) {
		log.info("Error executing statement: " + ex.getMessage());
		return ExceptionUtils.getRootCauseMessage(ex);
	}
	
	@ResponseBody
	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	String badCredentialsExceptionHandler(BadCredentialsException ex) {
		log.info("Error logging in: " + ex.getMessage());
		return ex.getMessage();
	}

	@ResponseBody
	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	String genericExceptionHandler(Exception ex) {
		log.error("Error treating request", ex);
		return ex.getMessage();
	}

}
