package com.restApplication.DAO;

import org.springframework.stereotype.Repository;
import com.restApplication.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>{
	
}
