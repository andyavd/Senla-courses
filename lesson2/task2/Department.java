import java.util.*;
import java.io.*;

public class Department implements IUnit{
	
	private String name;
	private Set employees = new HashSet();
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return this.name;
	}
	public Set getEmployees(){
		return employees;	
	}
	public void addEmployee(Employee newEmployee){
		employees.add(newEmployee);
		newEmployee.setDepartment(this);
	}
	public void removeEmployees(Employee em){
		employees.remove(em);
	}
	public int getPersonCount(){
		return getEmployees().size();
	}
	
	public Department(String nm){
		name = nm;
	}
}
