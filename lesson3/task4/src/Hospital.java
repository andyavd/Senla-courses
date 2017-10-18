public class Hospital implements IHospitalAbilities {

	private Doctor[] doctors = new Doctor[20];
	private Patient[] patients = new Patient[20];
	private int totalDoctors = 0;
	private int totalPatients = 0;

	public Doctor[] getDoctors() {
		return doctors;
	}

	public void setDoctors(Doctor[] doctors) {
		this.doctors = doctors;
	}
	
	
	public Patient[] getPatients() {
		return patients;
	}

	public void setPatients(Patient[] patients) {
		this.patients = patients;
	}

	public int getTotalDoctors() {
		return totalDoctors;
	}

	public int getTotalPatients() {
		return totalPatients;
	}

	@Override
	public void addDoctor(Doctor doctor) {
		doctors[totalDoctors] = doctor;
		totalDoctors++;
		System.out.println(doctor.toString() + " came to work today.");
	}

	@Override
	public void addPatient(Patient patient) {
		patients[totalPatients] = patient;
		totalPatients++;
		System.out.println(patient.toString() + " visited hospital today.");
	}

	@Override
	public Patient[] getAllDoctorsPatients(Doctor doctor) {
		return doctor.getDoctorsPatients();
	}
}
