package task4;

public interface IHospitalAbilities {
	
	public void addDoctor(Doctor doctor);
	public void addPatient(Patient patient);
	public Patient[] getAllDoctorsPatients(Doctor doctor);
}
