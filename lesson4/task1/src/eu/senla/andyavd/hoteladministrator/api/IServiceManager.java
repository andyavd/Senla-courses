package eu.senla.andyavd.hoteladministrator.api;

import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.entities.Service;

public interface IServiceManager {

	public void addService(Service service);

	public Service[] getServices();

	public Service[] sortServices(Comparator<Service> comparator);
	
}
