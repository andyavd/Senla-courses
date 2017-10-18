package task4;

public abstract class APerson {
	protected String firstName;
	protected String lastName;
	
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public APerson(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
}