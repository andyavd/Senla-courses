public class Patient extends APerson {

	public Patient(String firstName, String lastName) {
		super(firstName, lastName);
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Patient ");
		s.append(firstName);
		s.append(" ");
		s.append(lastName);
		return s.toString();
	}
}
