public class Test {

	public static void main(String[] args) {

		Hospital hospital = new Hospital();
		Printer print = new Printer();
		
		Doctor doctor1 = new Doctor("Gregory", "House");
		Doctor doctor2 = new Doctor("James", "Wilson");
		Doctor doctor3 = new Doctor("Eric", "Foreman");

		Patient patient1 = new Patient("John", "Doe");
		Patient patient2 = new Patient("James", "Doe");
		Patient patient3 = new Patient("Jane", "Doe");
		Patient patient4 = new Patient("Jack", "Doe");
		Patient patient5 = new Patient("Jim", "Doe");

		hospital.addDoctor(doctor1);
		hospital.addDoctor(doctor2);
		hospital.addDoctor(doctor3);

		hospital.addPatient(patient1);
		hospital.addPatient(patient2);
		hospital.addPatient(patient3);
		hospital.addPatient(patient4);
		hospital.addPatient(patient5);
		
		hospital.showAllDoctors();
		hospital.showAllPatients();

		doctor1.setDoctorsPatient(patient1);
		doctor1.setDoctorsPatient(patient2);
		doctor1.setDoctorsPatient(patient5);
		
		doctor2.setDoctorsPatient(patient3);
		
		doctor3.setDoctorsPatient(patient4);
		
		hospital.showProcess(doctor1);
		hospital.showProcess(doctor2);
		hospital.showProcess(doctor3);

		doctor1.removeDoctorPatient(patient1);
		doctor1.removeDoctorPatient(patient2);
		doctor2.removeDoctorPatient(patient3);
		doctor3.removeDoctorPatient(patient4);

	}
}
