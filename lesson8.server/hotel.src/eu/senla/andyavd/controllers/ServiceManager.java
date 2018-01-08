package eu.senla.andyavd.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.DependencyInjection;
import eu.senla.andyavd.Service;
import eu.senla.andyavd.api.controllers.IServiceManager;
import eu.senla.andyavd.api.storages.IServicesStorage;

public class ServiceManager implements IServiceManager {

	final static Logger logger = Logger.getLogger(ServiceManager.class);
	
	private IServicesStorage servicesStorage = (IServicesStorage) DependencyInjection.getInstance().getInstance(IServicesStorage.class);

	public ServiceManager() {
	}
	
	@Override
	public void addService(Service service) {
		servicesStorage.addService(service);
	}

	@Override
	public void deleteService(Service service) {
		servicesStorage.deleteService(service);
	}
	
	@Override
	public List<Service> getServices() {
		return servicesStorage.getServices();
	}
	
	@Override
	public void setServices(List<Service> services) {
		servicesStorage.setServices(services);
	}

	@Override
	public List<Service> sortServices(Comparator<Service> comparator) {
		List<Service> sortedServices = servicesStorage.getServices();
		Collections.sort(sortedServices, comparator);
		return sortedServices;
	}

	@Override
	public Service getServiceById(Integer id) {
		return servicesStorage.getServiceById(id);
	}

	@Override
	public void importFromCsv() {
		servicesStorage.importFromCsv();
	}
	
	@Override
	public void exportToCsv() {
		servicesStorage.exportToCsv();
	}
}
