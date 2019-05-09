
package com.restApplication.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.restApplication.DAO.EmployeeRepository;
import com.restApplication.controller.RestApplication;
import com.restApplication.model.Employee;

@RunWith(SpringRunner.class)
@WebMvcTest(RestApplication.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
public class TestEmployeeAPI {

	@Autowired
	private MockMvc mvc;
	
	@Autowired
	ObjectMapper mapper;
	
	private String URL = "http://localhost:9090/api/v1/employees";

	@MockBean
	EmployeeRepository employeeRepository;

	
	  @Test public void getAllEmployeesAPI() throws Exception {		  
		  
		  Employee empStub = new Employee(1l,"Shona", "Indai", "delhi");
		  Optional<Employee> op = Optional.of(empStub);
		  when(employeeRepository.findById(new Long(1))).thenReturn(op);
		 
		  
		  MvcResult result = mvc.perform(
	  MockMvcRequestBuilders.get(URL+"/{id}",new Long(1)).accept(
	  MediaType.APPLICATION_JSON_UTF8)) .andDo(print())
		  .andReturn();
		  
		  // verify
		  int status = result.getResponse().getStatus();
		  assertEquals("Incorrect Response Status", 
				  HttpStatus.OK.value(), status);
		  
		// verify that service method was called once
			verify(employeeRepository).findById(new Long(1));
		  
		 		  
			Employee resultEmployee = mapper.readValue(result.getResponse()
					.getContentAsString(), Employee.class);
			
			assertNotNull(resultEmployee);
			assertEquals(1l, resultEmployee.getEmoployeeId().longValue());
		  }
	
	
	
	
	@Test
	public void createEmployeeAPI() throws Exception {
		Employee empStub = new Employee(3l,"simren","delhi","sdfs");
		
		
		
		when(employeeRepository.save(any(Employee.class))).thenReturn(empStub);

		MvcResult result = mvc
				.perform(MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON_UTF8)
						.accept(MediaType.APPLICATION_JSON_UTF8).content(mapper.writeValueAsBytes(empStub)))
				.andReturn();

		// verify 
		int status = result.getResponse().getStatus();
		assertEquals("Incorrect Response Status", HttpStatus.OK.value(),status);

		// verify that service method was called once
		verify(employeeRepository).save(empStub);

		Employee resultEmployee = mapper.readValue(result.getResponse().getContentAsString(), Employee.class);

		assertNotNull(resultEmployee);
		assertEquals("simren", resultEmployee.getEmpName());

	}
	 
	 
	  
	
	 
}
