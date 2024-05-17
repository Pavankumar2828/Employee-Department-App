package org.jsp.empapp.dao;

import java.util.Optional;

import org.jsp.empapp.dto.Department;
import org.jsp.empapp.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class DepartmentDao {
	@Autowired
	private DepartmentRepository deptRepository;
	
	public Department save(Department dept) {
		return deptRepository.save(dept);
	}
	
	public Department update(Department dept) {
		return deptRepository.save(dept);
	}
	
	public Optional<Department> get(int id) {
		return deptRepository.findById(id);
	}
	
	public Optional<Department> findByname(String name){
		return deptRepository.findByName(name);
	}
	
}
