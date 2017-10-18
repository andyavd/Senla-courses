package task4;

public class Printer {
	Hospital hospital = new Hospital();

	public void getResults(Doctor doctor) {
		System.out.println(doctor.toString() + doctor.isAnyoneLeft());
	}

	public void getProcess(Doctor doctor) {
		System.out.println(doctor.toString() + " works today with " + doctor.getTotalDoctorsPatients()
				+ doctor.changeTheWordPatient());
	}

	public void getDoctors(int totalDoctors) {
		System.out.println("Today are working " + totalDoctors + " doctors.");
	}

	public void getPatients(int totalPatients) {
		System.out.println("Today are visiting " + totalPatients + " patients.");
	}
}
