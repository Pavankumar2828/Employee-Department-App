package org.jsp.empapp.dao;

import java.util.List;
import java.util.Optional;

import org.jsp.empapp.dto.Employee;
import org.jsp.empapp.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class EmployeeDao {
	@Autowired
	private EmployeeRepository employeeRepository;
	
	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Employee update(Employee employee) {
		return employeeRepository.save(employee);
	}
	
	public Optional<Employee> get(int id) {
		return employeeRepository.findById(id);
	}
	
	public List<Employee> findByDepartmentId(int id){
		return employeeRepository.findByDepartmentId(id);
	}
	
	public List<Employee> findByDepartmentName(String name){
		return employeeRepository.findByDepartmentName(name);
	}
	
	public List<Employee> findByName(String name){
		return employeeRepository.findByName(name);
	}
	
	public List<Employee> findBySalary(double salary){
		return employeeRepository.findBySalary(salary);
	}
	
	public List<Employee> findByDesignation(String designation){
		return employeeRepository.findByDesignation(designation);
	}
	
	public List<Employee> findbySalaryRange(double low, double high){
		return employeeRepository.findBySalaryRange(low, high);
	}
	
}
