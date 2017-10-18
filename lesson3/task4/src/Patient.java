public class Patient extends APerson {

	private String disease;
	
	public String getDisease() {
		return disease;
	}
	public void setDisease(String disease) {
		this.disease = disease;
	}
	
	public Patient(String firstName, String lastName, String disease) {
		super(firstName, lastName);
		this.disease = disease;
	}
	
	@Override
	public String toString() {
		return "Patient " + firstName + " " + lastName + ", diagnosis - " + disease + ", ";
	}
}
