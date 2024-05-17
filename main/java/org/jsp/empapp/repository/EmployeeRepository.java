package org.jsp.empapp.repository;

import java.util.List;

import org.jsp.empapp.dto.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	
	List<Employee> findByName(String name);
	
	List<Employee> findBySalary(double salary);
	
	List<Employee> findByDesignation(String designation);
	
	@Query("select e from Employee e where e.dept.id=?1")
	List<Employee> findByDepartmentId(int id);
	
	@Query("select e from Employee e where e.dept.name=?1")
	List<Employee> findByDepartmentName(String dept_name);
	
	@Query("select e from Employee e where e.salary>=?1 and e.salary<=?2")
	List<Employee> findBySalaryRange(double low, double high);
	
}
