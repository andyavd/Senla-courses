public class Hospital implements IHospitalAbilities {
	
	Printer printer = new Printer();
	
	private Doctor[] doctors = new Doctor[20];
	private Patient[] patients = new Patient[20];
	private int totalDoctors = 0;
	private int totalPatients = 0;

	public int getTotalDoctors() {
		return totalDoctors;
	}

	public void showAllDoctors() {
		StringBuilder s = new StringBuilder();
		s.append("Today are working ");
		s.append(totalDoctors);
		s.append(" doctors.");
		
		Printer.print(s.toString());
	}
	
	public int getTotalPatients() {
		return totalPatients;
	}
	
	public void showAllPatients() {
		StringBuilder s = new StringBuilder();
		s.append("Today are visiting ");
		s.append(totalPatients);
		s.append(" patients.");
		
		Printer.print(s.toString());
	}
	
	public void showProcess(Doctor doctor) {
		String patient;
		
		if(doctor.getAllDoctorsPatients()==1) {
			patient = " Patient";
		} else {
			patient = " Patients";
		}
		
		StringBuilder s = new StringBuilder();
		s.append(doctor);
		s.append(" works today with ");
		s.append(doctor.getAllDoctorsPatients());
		s.append(patient);
		
		Printer.print(s.toString());
	}

	@Override
	public void addDoctor(Doctor doctor) {
		doctors[totalDoctors] = doctor;
		totalDoctors++;
		
		StringBuilder s = new StringBuilder();
		s.append(doctor);
		s.append(" came to work today.");
		
		Printer.print(s.toString());
	}

	@Override
	public void addPatient(Patient patient) {
		patients[totalPatients] = patient;
		totalPatients++;
		
		StringBuilder s = new StringBuilder();
		s.append(patient);
		s.append(" visited hospital today.");
		
		Printer.print(s.toString());
	}

	@Override
	public Patient[] getAllDoctorsPatients(Doctor doctor) {
		return doctor.getDoctorsPatients();
	}
}
