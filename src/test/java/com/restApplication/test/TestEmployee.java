package com.restApplication.test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.restApplication.DAO.EmployeeRepository;
import com.restApplication.model.Employee;

@RunWith(SpringRunner.class)
@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestEmployee {
	
	
	@Autowired
    private TestEntityManager entityManager;
 
    @Autowired
    private EmployeeRepository employeeRepository;
	 
	 @Test
	 public void getAllEmployeesAPI() throws Exception
	 {
		 Employee alex = new Employee("alex","US","Michegon");
		    entityManager.persist(alex);
		    entityManager.flush();
		  
		    
		Optional<Employee> found = employeeRepository.findById(alex.getEmoployeeId());
		Employee Emp = found.get();
		assertThat(Emp.getEmpName()).isEqualTo(alex.getEmpName());
	 }
	 
	
	@Test
	public void testSaveEmployee() throws Exception {
		Employee empStub = new Employee(2l,"Aaveya", "Indai", "delhi");
		
		Employee result=employeeRepository.save(empStub);
		
		assertThat(result).isNotNull();
		assertEquals("Aaveya", result.getEmpName());
	}
	 
}
