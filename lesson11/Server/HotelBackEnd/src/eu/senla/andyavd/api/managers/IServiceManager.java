package eu.senla.andyavd.api.managers;

import java.util.List;

import eu.senla.andyavd.Service;
import eu.senla.andyavd.enums.SortType;

 public interface IServiceManager {

	void addService(Service service) throws Exception;
	List<Service> getServices() throws Exception;
	void updateService(Service service) throws Exception;
	void deleteService(int id) throws Exception;
	Service getServiceById(int id) throws Exception;
	void changeServicePrice(int id, double dailyPrice) throws Exception;
	List<Service> sortServices(SortType type) throws Exception;
	void importFromCsv() throws Exception;
	void exportToCsv() throws Exception;
}