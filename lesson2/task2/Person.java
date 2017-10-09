import java.util.*;
import java.io.*;

public class Person{

	protected String firstName;
	protected String lastName;

	public void setFirstName (String newFirstName){
		firstName = newFirstName;
	}
	public String getFirstName(){
		return firstName;
	}
	public void setLastName (String newLastName){
		lastName = newLastName;
	}
	public String getLastName(){
		return lastName;
	}

}