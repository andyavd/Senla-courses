public class Test {

	public static void main(String[] args) {

		Hospital hospital = new Hospital();
		Printer print = new Printer();
		
		Doctor doctor1 = new Doctor("Gregory", "House", "Diagnostican");
		Doctor doctor2 = new Doctor("James", "Wilson", "Oncologist");
		Doctor doctor3 = new Doctor("Eric", "Foreman", "Surgeon");

		Patient patient1 = new Patient("John", "Doe", "Flu");
		Patient patient2 = new Patient("James", "Doe", "Migraine");
		Patient patient3 = new Patient("Jane", "Doe", "Adenoma");
		Patient patient4 = new Patient("Jack", "Doe", "Inflammation of appendicitis");
		Patient patient5 = new Patient("Jim", "Doe", "Something strange");

		hospital.addDoctor(doctor1);
		hospital.addDoctor(doctor2);
		hospital.addDoctor(doctor3);

		hospital.addPatient(patient1);
		hospital.addPatient(patient2);
		hospital.addPatient(patient3);
		hospital.addPatient(patient4);
		hospital.addPatient(patient5);
		
		print.getDoctors(hospital.getTotalDoctors());
		print.getDoctors(hospital.getTotalPatients());

		doctor1.setDoctorsPatients(patient1);
		doctor1.setDoctorsPatients(patient2);
		doctor1.setDoctorsPatients(patient5);
		print.getProcess(doctor1);

		doctor2.setDoctorsPatients(patient3);
		print.getProcess(doctor2);

		doctor3.setDoctorsPatients(patient4);
		print.getProcess(doctor3);

		doctor1.removeDoctorPatients(patient1);
		doctor1.removeDoctorPatients(patient2);
		doctor2.removeDoctorPatients(patient3);
		doctor3.removeDoctorPatients(patient4);

		print.getResults(doctor1);
		print.getResults(doctor2);
		print.getResults(doctor3);
	}
}
