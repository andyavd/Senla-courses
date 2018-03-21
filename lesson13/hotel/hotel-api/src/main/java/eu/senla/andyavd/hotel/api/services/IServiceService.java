package eu.senla.andyavd.hotel.api.services;

import java.util.List;

import eu.senla.andyavd.hotel.entity.beans.Service;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IServiceService {

	void addService(Service service) throws Exception;

	List<Service> getServices(SortType type) throws Exception;

	void updateService(Service service) throws Exception;

	void deleteService(Service service) throws Exception;

	Service getServiceById(int id) throws Exception;

	void changeServicePrice(int id, double dailyPrice) throws Exception;

	void importFromCsv() throws Exception;

	void exportToCsv() throws Exception;
}