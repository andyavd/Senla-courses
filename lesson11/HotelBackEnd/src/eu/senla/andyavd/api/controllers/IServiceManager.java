package eu.senla.andyavd.api.controllers;

import java.util.List;

import eu.senla.andyavd.Service;
import eu.senla.andyavd.enums.SortType;

 public interface IServiceManager {

	void addService(Service service);
	List<Service> getServices();
	void updateService(Service service);
	void deleteService(int id);
	Service getServiceById(int id);
	void changeServicePrice(int id, double dailyPrice);
	List<Service> sortServices(SortType type);
	void importFromCsv();
	void exportToCsv();
}