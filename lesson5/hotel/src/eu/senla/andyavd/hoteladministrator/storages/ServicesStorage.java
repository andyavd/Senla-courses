package eu.senla.andyavd.hoteladministrator.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.storages.IServicesStorage;
import eu.senla.andyavd.hoteladministrator.entities.Service;

public class ServicesStorage implements IServicesStorage {

	private List<Service> services = new ArrayList<Service>();

	private static ServicesStorage servicesStorage;

	private ServicesStorage() {

	}

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
		services.get(services.size() - 1).setId(services.size());
		services.add(service);
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
