package com.tcs.ins.testcase.api.model;

import java.util.ArrayList;
import java.util.List;

public class ErrorMessages {

	private final List<ErrorMessage> errors = new ArrayList<>();

	public void add(ErrorMessage errorMessage) {
		errors.add(errorMessage);
	}

	public List<ErrorMessage> getErrors() {
		return errors;
	}

	public void add(String statusCode, String errorMessage) {
		add(new ErrorMessage(statusCode, errorMessage));
	}
}