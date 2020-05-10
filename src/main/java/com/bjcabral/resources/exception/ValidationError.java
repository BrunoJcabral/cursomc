package com.bjcabral.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{

	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> errors = new ArrayList<>();	
	
	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldName, String message) {
		this.errors.add(new FieldMessage(fieldName, message));
	}	
	
	public ValidationError(Integer status, String msg, Long timestamp) {
		super(status, msg, timestamp);
		// TODO Auto-generated constructor stub
	}



	
}
