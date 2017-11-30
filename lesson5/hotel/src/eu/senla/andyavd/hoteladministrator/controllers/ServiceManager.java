package eu.senla.andyavd.hoteladministrator.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.api.controllers.IServiceManager;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.storages.ServicesStorage;

public class ServiceManager implements IServiceManager {

	final static Logger logger = Logger.getLogger(ServiceManager.class);
	
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
