import java.util.*;
import java.io.*;

public class Employee extends Person {

	private String position;
	private IdCard idCard;
	private Department department;

	public void setPosition(String position){
		this.position = position;
	}
	public String getPosition(){
		return position;
	}
	public void setIdCard(IdCard idCard){
		this.idCard = idCard;
	}
	public IdCard getIdCard(){
		return idCard;
	}
	public void setDepartment(Department department){
		this.department = department;
	}
	public Department getDepartment(){
		return department;
	}

	public Employee(String fn, String ln, String pn){
		firstName = fn;
		lastName = ln;
		position = pn;
	}

	
}
