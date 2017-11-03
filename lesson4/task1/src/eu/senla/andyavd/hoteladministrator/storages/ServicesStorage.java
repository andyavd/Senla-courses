package eu.senla.andyavd.hoteladministrator.storages;

import java.util.Arrays;
import eu.senla.andyavd.hoteladministrator.entities.Entity;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;

public class ServicesStorage {

	private Service[] services = new Service[4];
	private int counter = 0;
	
	private Service[] castEntitiesArray(Entity[] entities) {
		Service[] serviceArray = Arrays.copyOf(entities, entities.length, Service[].class);
		return serviceArray;
	}

	public void addService(Service service) {
		 if (services[services.length-1] != null) {
		 services =  castEntitiesArray(ArrayWorker.expandArray(services));
		 }
		services[counter] = service;
		services[counter].setId(counter + 1);
		counter++;
	}

	public Service[] getServices() {
		return services;
	}
}
