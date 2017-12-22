package lesson6.hotel.src.eu.senla.andyavd.storages;

import java.util.ArrayList;
import java.util.List;

import lesson6.hotel.src.eu.senla.andyavd.api.storages.IServicesStorage;
import lesson6.hotel.src.eu.senla.andyavd.entities.Service;

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
	public void addService(Service service) {
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
	public void deleteService(Service service) {
		for (int i = 0; i < services.size(); i++) {
			if (services.get(i) == service) {
				services.remove(i);
			}
		}
	}

	@Override
	public void setServices(List<Service> services) {
		this.services = services;

	}
}
