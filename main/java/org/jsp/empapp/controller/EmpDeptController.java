	package org.jsp.empapp.controller;

import java.util.List;

import org.jsp.empapp.dto.Department;
import org.jsp.empapp.dto.Employee;
import org.jsp.empapp.dto.ResponseStructure;
import org.jsp.empapp.service.EmpDeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/emp-dept/")
public class EmpDeptController {
	@Autowired
	private EmpDeptService service;
	
	@PostMapping("/dept")
	public ResponseEntity<ResponseStructure<Department>> saveDepartment(@RequestBody Department dept) {
		return service.saveDepartment(dept);
	}
	
	@PostMapping("/employees")
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(@RequestBody Employee employee,@RequestParam int dept_id) {
		return service.saveEmployee(employee, dept_id);
	}
	
	@PutMapping("/dept")
	public ResponseEntity<ResponseStructure<Department>> updateDepartment(@RequestBody Department dept) {
		return service.updateDepartment(dept);
	}
	
	@PutMapping("/employees")
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(@RequestBody Employee employee) {
		return service.updateEmployee(employee);
	}
	
	@GetMapping("/employees/dept/{id}")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesByDepartmentId(@PathVariable int id) {
		return service.findEmployeesByDepartmentId(id);
	}
	
	@GetMapping("/employees/dept")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesByDepartmentName(@RequestParam String name) {
		return service.findEmployeesByDepartmentName(name);
	}
	
	@GetMapping("/employees/find-by-name")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesByName(@RequestParam String name) {
		return service.findEmployeesByName(name);
	}
	
	@GetMapping("/employees/find-by-salary")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesBySalary(@RequestParam double salary) {
		return service.findEmployeesBySalary(salary);
	}
	
	@GetMapping("employees/find-by-designation")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesByDesignation(@RequestParam String designation) {
		return service.findEmployeesByDesignation(designation);
	}
	
	@GetMapping("employees/find-by-salary-range")
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesBySalaryRange(@RequestParam double low,@RequestParam double high) {
		return service.findEmployeesBySalaryRange(low, high);
	}
	
	
}
