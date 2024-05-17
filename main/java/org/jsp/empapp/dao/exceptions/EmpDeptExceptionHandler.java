package org.jsp.empapp.dao.exceptions;

import org.jsp.empapp.dto.ResponseStructure;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class EmpDeptExceptionHandler extends ResponseEntityExceptionHandler {
	@ExceptionHandler(DepartmentNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleDNFE(DepartmentNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody("Department Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
	@ExceptionHandler(EmployeeNotFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleENFE(EmployeeNotFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody("Employee Not Found");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
	@ExceptionHandler(NoEmployeeFoundException.class)
	public ResponseEntity<ResponseStructure<String>> handleNEFE(NoEmployeeFoundException exception) {
		ResponseStructure<String> structure = new ResponseStructure<>();
		structure.setBody("Employees not present");
		structure.setMessage(exception.getMessage());
		structure.setStatusCode(HttpStatus.NOT_FOUND.value());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(structure);
	}
	
}
