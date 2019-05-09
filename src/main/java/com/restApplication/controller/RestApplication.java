package com.restApplication.controller;
  
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.restApplication.DAO.EmployeeRepository;
import com.restApplication.Exception.ResourceNotFoundException;
import com.restApplication.model.Employee;
  
@RequestMapping(path="/api/v1")
@RestController 
  public class RestApplication {
  
  @Autowired
  private EmployeeRepository employeeRepository;
  
  @RequestMapping(path="/hello",method = RequestMethod.GET)  
  public String welcome() 
  { 
	  return  "hello User";  
  }
  
  @GetMapping("/employees")
  @ResponseBody
  public List<Employee> getAllEmployees() {
      return employeeRepository.findAll();	  
  }
  
  @GetMapping("/employees/{id}")
  public ResponseEntity<Employee> getEmployeeById(@PathVariable(value = "id") Long employeeId)
      throws ResourceNotFoundException {
      Employee employee = employeeRepository.findById(employeeId)
        .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
      return ResponseEntity.ok().body(employee);
  }
  
  @PostMapping("/employees")
  public Employee createEmployee(@Valid @RequestBody Employee employee)throws ResourceNotFoundException  {
      return employeeRepository.save(employee);
  }
  
  @PutMapping("/employees/{id}")
  public ResponseEntity<Employee> updateEmployee(@PathVariable(value = "id") Long employeeId,
       @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
      Employee employee = employeeRepository.findById(employeeId)
      .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

      employee.setEmpName(employeeDetails.getEmpName());
      employee.setEmpCountry(employeeDetails.getEmpCountry());
      employee.setEmpCity(employeeDetails.getEmpCity());
      final Employee updatedEmployee = employeeRepository.save(employee);
      return ResponseEntity.ok(updatedEmployee);
  }
  
  @DeleteMapping("/employees/{id}")
  public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
       throws ResourceNotFoundException {
      Employee employee = employeeRepository.findById(employeeId)
     .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));

      employeeRepository.delete(employee);
      Map<String, Boolean> response = new HashMap<>();
      response.put("deleted", Boolean.TRUE);
      return response;
  }
  }
 