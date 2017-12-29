package eu.senla.andyavd.api.controllers;

import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.entities.Service;

public interface IServiceManager {

	public void addService(Service service);

	public List<Service> getServices();
	
	public void setServices(List <Service> services);

	public List<Service> sortServices(Comparator<Service> comparator);
	
	public Service getServiceById(Integer id);

	public void deleteService(Service service);
	
}
