/*
 * Copyright (C) 2016 TopCoder Inc., All rights reserved.
 */
package com.topcoder.web.common.cache;

/**
 * This exception will be thrown if the configuration is invalid.
 * 
 * @author TCSDEVELOPER
 * @version 1.0
 */
public class ConfigurationException extends RuntimeException {
	
	/**
	 * Constructor with the message.
	 * 
	 * @param message the message
	 */
	public ConfigurationException(String message) {
		super(message);
	}
	
	/**
	 * Constructor with the message and inner cause.
	 * 
	 * @param message the message
	 * @param cause the inner cause
	 */
	public ConfigurationException(String message, Throwable cause) {
		super(message, cause);
	}
	
}
