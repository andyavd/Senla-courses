package lesson6.hotel.src.eu.senla.andyavd.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.api.controllers.IServiceManager;
import lesson6.hotel.src.eu.senla.andyavd.entities.Service;
import lesson6.hotel.src.eu.senla.andyavd.storages.ServicesStorage;

public class ServiceManager implements IServiceManager {

	final static Logger logger = Logger.getLogger(ServiceManager.class);

	public ServiceManager() {
	}
	
	@Override
	public void addService(Service service) {
		ServicesStorage.getInstance().addService(service);
	}

	@Override
	public void deleteService(Service service) {
		ServicesStorage.getInstance().deleteService(service);
	}
	
	@Override
	public List<Service> getServices() {
		return ServicesStorage.getInstance().getServices();
	}
	
	@Override
	public void setServices(List<Service> services) {
		ServicesStorage.getInstance().setServices(services);
	}

	@Override
	public List<Service> sortServices(Comparator<Service> comparator) {
		List<Service> sortedServices = ServicesStorage.getInstance().getServices();
		Collections.sort(sortedServices, comparator);
		return sortedServices;
	}

	@Override
	public Service getServiceById(Integer id) {
		return ServicesStorage.getInstance().getServiceById(id);
	}
	
}
