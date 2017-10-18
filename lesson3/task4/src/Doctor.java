package task4;

public class Doctor extends APerson implements IDoctorsAbilities {
	private String specialization;
	private Patient[] doctorsPatients = new Patient[10];
	private int totalDoctorsPatients = 0;
	
	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	
	public String changeTheWordPatient() {
		if(totalDoctorsPatients == 1) {
			return " Patient";
		} else {
			return " Patients";
		}
	}
	
	public Doctor(String firstName, String lastName, String specialization) {
		super(firstName, lastName);
		this.specialization = specialization;
	}

	public Patient[] getDoctorsPatients() {
		return doctorsPatients;
	}

	@Override
	public void setDoctorsPatients(Patient patient) {
		doctorsPatients[totalDoctorsPatients] = patient;
		totalDoctorsPatients++;
	}
	
	@Override
	public void removeDoctorPatients(Patient patient) {
		for(int i=0; i<doctorsPatients.length; i++) {
			if(doctorsPatients[i] == patient) {
				doctorsPatients[i] = null;
				totalDoctorsPatients--;
			}
		}
	}

	@Override
	public String toString() {
		return "Doctor " + firstName + " " + lastName + ", " + specialization + "";
	}
	
	@Override
	public String isAnyoneLeft() {
		if(totalDoctorsPatients == 0) {
			return " has cured all the patients.";
		} else {
			return " has " + totalDoctorsPatients + changeTheWordPatient() +" for tomorrow.";
		}
		
	}
	
	public int getTotalDoctorsPatients() {
		return totalDoctorsPatients;
	}
}