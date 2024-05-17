package org.jsp.empapp.repository;

import java.util.Optional;

import org.jsp.empapp.dto.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
	Optional<Department> findByName(String name);
}
