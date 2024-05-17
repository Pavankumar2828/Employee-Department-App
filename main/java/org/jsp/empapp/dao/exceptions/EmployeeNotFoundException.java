package org.jsp.empapp.dao.exceptions;

public class EmployeeNotFoundException extends RuntimeException {
	public EmployeeNotFoundException(String message) {
		super(message);
	}
}
