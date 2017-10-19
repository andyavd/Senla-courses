public class Doctor extends APerson implements IDoctorsAbilities {
	private Patient[] doctorsPatients = new Patient[10];
	private int totalDoctorsPatients = 0;
	
	public Doctor(String firstName, String lastName) {
		super(firstName, lastName);
	}

	public Patient[] getDoctorsPatients() {
		return doctorsPatients;
	}

	@Override
	public void setDoctorsPatient(Patient patient) {
		doctorsPatients[totalDoctorsPatients] = patient;
		totalDoctorsPatients++;
	}
	
	@Override
	public void removeDoctorPatient(Patient patient) {
		for(int i=0; i<doctorsPatients.length; i++) {
			if(doctorsPatients[i] == patient) {
				doctorsPatients[i] = null;
				totalDoctorsPatients--;
			}
		}
	}

	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		s.append("Doctor ");
		s.append(firstName);
		s.append(" ");
		s.append(lastName);
		return s.toString();
	}
	
	public int getAllDoctorsPatients() {
		return totalDoctorsPatients;
	}
}
