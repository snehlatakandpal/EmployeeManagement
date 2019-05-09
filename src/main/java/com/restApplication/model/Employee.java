package com.restApplication.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Employees")
public class Employee {
	
	
	 private Long emoployeeId;
	 
	 public Employee(Long emoployeeId, String empName, String empCountry, String empCity) {		
		super();
		 this.emoployeeId = emoployeeId;
		this.empName = empName;
		this.empCountry = empCountry;
		this.empCity = empCity;
	}


	@Id
	 @GeneratedValue
	 @Column(name = "EmployeeID", unique = true, nullable = false)
	public Long getEmoployeeId() {
		return emoployeeId;
	}


	public void setEmoployeeId(Long emoployeeId) {
		this.emoployeeId = emoployeeId;
	}
	private String empName;
	private String empCountry;
	private String empCity;
	public Employee(String empName, String empCountry, String empCity) {
		super();
		this.empName = empName;
		this.empCountry = empCountry;
		this.empCity = empCity;
	}
	
	
	public Employee()
	{super();}
	
	@Override
	public String toString() {
		return "Employee [empName=" + empName + ", empCountry=" + empCountry + ", empCity=" + empCity + "]";
	}

	@Column(name = "employee_name", nullable = false)
	public String getEmpName() {
		return empName;
	}
	

	public void setEmpName(String empName) {
		this.empName = empName;
	}
	
	@Column(name = "employee_country", nullable = false)
	public String getEmpCountry() {
		return empCountry;
	}
	public void setEmpCountry(String empCountry) {
		this.empCountry = empCountry;
	}
	
	@Column(name = "city", nullable = false)
	public String getEmpCity() {
		return empCity;
	}
	public void setEmpCity(String empCity) {
		this.empCity = empCity;
	}
	
	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee emp = (Employee) o;

        if (emoployeeId != null ? !emoployeeId.equals(emp.emoployeeId) : emp.emoployeeId != null) return false;
        if (empName != null ? !empName.equals(emp.empName) : emp.empName != null) return false;
        if (empCity != null ? !empCity.equals(emp.empCity) : emp.empCity != null) return false;
        return empCountry != null ? empCountry.equals(emp.empCountry) : emp.empCountry == null;
    }

    @Override
    public int hashCode() {
        int result = emoployeeId != null ? emoployeeId.hashCode() : 0;
        result = 31 * result + (empName != null ? empName.hashCode() : 0);
        result = 31 * result + (empCity != null ? empCity.hashCode() : 0);
        return result;
    }

}
