package org.jsp.empapp.dao.exceptions;

public class NoEmployeeFoundException extends RuntimeException {
	public NoEmployeeFoundException(String message) {
		super(message);
	}
}
