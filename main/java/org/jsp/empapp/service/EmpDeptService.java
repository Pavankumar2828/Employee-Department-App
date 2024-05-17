package org.jsp.empapp.service;

import java.util.List;
import java.util.Optional;

import org.jsp.empapp.dao.DepartmentDao;
import org.jsp.empapp.dao.EmployeeDao;
import org.jsp.empapp.dao.exceptions.DepartmentNotFoundException;
import org.jsp.empapp.dao.exceptions.EmployeeNotFoundException;
import org.jsp.empapp.dao.exceptions.NoEmployeeFoundException;
import org.jsp.empapp.dto.Department;
import org.jsp.empapp.dto.Employee;
import org.jsp.empapp.dto.ResponseStructure;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmpDeptService {
	@Autowired
	private DepartmentDao deptDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	public ResponseEntity<ResponseStructure<Department>> saveDepartment(Department dept) {
		ResponseStructure<Department> structure = new ResponseStructure<>();
		structure.setBody(deptDao.save(dept));
		structure.setMessage("Department Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<Department>> updateDepartment(Department dept) {
		ResponseStructure<Department> structure = new ResponseStructure<>();
		Optional<Department> recDept = deptDao.get(dept.getId());
		if(recDept.isPresent()) {
			Department dbDept = recDept.get();
			dbDept.setName(dept.getName());
			dbDept.setLocation(dept.getLocation());
			structure.setBody(deptDao.save(dept));
			structure.setMessage("Department updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new DepartmentNotFoundException("Cannot update department as id is invalid");
	}
	
	public ResponseEntity<ResponseStructure<Employee>> saveEmployee(Employee employee, int dept_id) {
		ResponseStructure<Employee> structure = new ResponseStructure<>();
		Optional<Department> recDept = deptDao.get(dept_id);
		if(recDept.isEmpty()) {
			throw new DepartmentNotFoundException("Cannot save Employee as department id is invalid");
		}
		Department dbDept = recDept.get();
		dbDept.getEmployees().add(employee);
		employee.setDept(dbDept);
		deptDao.save(dbDept);
		structure.setBody(employeeDao.save(employee));
		structure.setMessage("Employee Saved");
		structure.setStatusCode(HttpStatus.CREATED.value());
		return ResponseEntity.status(HttpStatus.CREATED).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<Employee>> updateEmployee(Employee employee) {
		ResponseStructure<Employee> structure = new ResponseStructure<>();
		Optional<Employee> recEmp = employeeDao.get(employee.getId());
		if(recEmp.isPresent()) {
			Employee dbEmp = recEmp.get();
			dbEmp.setDesignation(employee.getDesignation());
			dbEmp.setName(employee.getName());
			dbEmp.setPassword(employee.getPassword());
			dbEmp.setPhone(employee.getPhone());
			dbEmp.setSalary(employee.getSalary());
			
			structure.setBody(employeeDao.save(dbEmp));
			structure.setMessage("Employee updated");
			structure.setStatusCode(HttpStatus.ACCEPTED.value());
			
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(structure);
		}
		throw new EmployeeNotFoundException("Cannot update employee as id is invalid");
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesByDepartmentId(int id) {
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		Optional<Department> recDept = deptDao.get(id);
		if(recDept.isPresent()) {
			List<Employee> employees = employeeDao.findByDepartmentId(id);
			if(employees.isEmpty()) {
				throw new NoEmployeeFoundException("No Employee present in given department");
			}
			structure.setBody(employees);
			structure.setMessage("List of Employees in given Department");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return ResponseEntity.status(HttpStatus.FOUND).body(structure);
		}
		throw new DepartmentNotFoundException("Cannot find employees since deepartment id is invalid");
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesByDepartmentName(String name) {
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		Optional<Department> recDept = deptDao.findByname(name);
		if(recDept.isPresent()) {
			List<Employee> employees = employeeDao.findByDepartmentName(name);
			if(employees.isEmpty()) {
				throw new NoEmployeeFoundException("No Employee Found with given department");
			}
			structure.setBody(employees);
			structure.setMessage("List of Employees in given Department");
			structure.setStatusCode(HttpStatus.FOUND.value());
			return ResponseEntity.status(HttpStatus.FOUND).body(structure);
		}
		throw new DepartmentNotFoundException("Cannot find employees since department name is invalid");
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesByName(String name) {
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		List<Employee> employees = employeeDao.findByName(name);
		if(employees.isEmpty()) {
			throw new NoEmployeeFoundException("Cannot find employees with given name");
		}
		structure.setBody(employees);
		structure.setMessage("List of Employees found with given name");
		structure.setStatusCode(HttpStatus.FOUND.value());
		return ResponseEntity.status(HttpStatus.FOUND).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesBySalary(double salary) {
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		List<Employee> employees = employeeDao.findBySalary(salary);
		if(employees.isEmpty()) {
			throw new NoEmployeeFoundException("Cannot find employees with given salary");
		}
		structure.setBody(employees);
		structure.setMessage("List of Employees found with given salary");
		structure.setStatusCode(HttpStatus.FOUND.value());
		return ResponseEntity.status(HttpStatus.FOUND).body(structure);
	}

	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesByDesignation(String designation) {
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		List<Employee> employees = employeeDao.findByDesignation(designation);
		if(employees.isEmpty()) {
			throw new NoEmployeeFoundException("Cannot find employees with given designation");
		}
		structure.setBody(employees);
		structure.setMessage("List of Employees found with given designation");
		structure.setStatusCode(HttpStatus.FOUND.value());
		return ResponseEntity.status(HttpStatus.FOUND).body(structure);
	}
	
	public ResponseEntity<ResponseStructure<List<Employee>>> findEmployeesBySalaryRange(double low, double high) {
		ResponseStructure<List<Employee>> structure = new ResponseStructure<>();
		List<Employee> employees = employeeDao.findbySalaryRange(low, high);
		if(employees.isEmpty()) {
			throw new NoEmployeeFoundException("No Employee has salary within given range");
		}
		structure.setBody(employees);
		structure.setMessage("List of Employees found with given name");
		structure.setStatusCode(HttpStatus.FOUND.value());
		return ResponseEntity.status(HttpStatus.FOUND).body(structure);
	}
	
}
