package hotel.src.eu.senla.andyavd.storages;

import java.util.ArrayList;
import java.util.List;

import annotations.src.eu.senla.andyavd.annotations.Storage;
import hotel.src.eu.senla.andyavd.api.storages.IServicesStorage;
import hotel.src.eu.senla.andyavd.entities.Service;
import hotel.src.eu.senla.andyavd.utils.csvutils.CommonCSVReader;
import hotel.src.eu.senla.andyavd.utils.csvutils.CommonCSVWriter;

@Storage
public class ServicesStorage implements IServicesStorage {

	private List<Service> services = new ArrayList<Service>();

	private static ServicesStorage servicesStorage;

	public static ServicesStorage getInstance() {
		if (servicesStorage == null) {
			servicesStorage = new ServicesStorage();
		}
		return servicesStorage;
	}

	@Override
	public List<Service> getServices() {
		return services;
	}

	@Override
	public synchronized void addService(Service service) {
		services.add(service);
		services.get(services.size() - 1).setId(services.size());
	}

	@Override
	public Service getServiceById(Integer id) {

		Service service = null;

		for (int i = 0; i < services.size(); i++) {
			if ((services.get(i)).getId() == id) {
				service = services.get(i);
			}
		}
		return service;
	}

	@Override
	public synchronized void deleteService(Service service) {
		for (int i = 0; i < services.size(); i++) {
			if (services.get(i) == service) {
				services.remove(i);
			}
		}
	}

	@Override
	public synchronized void setServices(List<Service> services) {
		this.services = services;

	}
	
	@Override
	public void importFromCsv() {
		@SuppressWarnings("unchecked")
		List<Service> importedServices = (List<Service>) CommonCSVReader.readFromFile(Service.class);
		for (Service service : importedServices) {
			if (!services.contains(service)) {
				services.add(service);
			}
		}
	}

	@Override
	public void exportToCsv() {
		CommonCSVWriter.writeToFile(services);
	}
}
