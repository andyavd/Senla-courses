package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;

public class ServicesStorage {

	private Service[] services = new Service[10];
	private int counter = 0;

	public void addService(Service service) {
		 if (services[services.length-1] != null) {
		 services = (Service[]) ArrayWorker.expandService(services);
		 }
		services[counter] = service;
		services[counter].setId(counter + 1);
		counter++;
	}

	public Service[] getServices() {
		return services;
	}
}
