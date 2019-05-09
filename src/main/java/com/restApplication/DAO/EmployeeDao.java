package com.restApplication.DAO;

import java.util.List;

import org.springframework.stereotype.Component;

import com.restApplication.model.Employee;

@Component
public class EmployeeDao {
	
	public List<Employee> findAll(){
		return (List<Employee>) new Employee();
	}
	
	public Employee save(Employee emp)
	{
		
		return emp;
		
	}
	
	public Employee find(Employee emp)
	{
		return new Employee();
	}

}
