package eu.senla.andyavd.hoteladministrator.model.storages;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.Service;

import java.util.ArrayList;

public class ServicesStorage {

	private List <Entity> services = new ArrayList<Entity>();
	private int counter = 0;

	public List<Entity> getServices() {
		return services;
	}

	public void addService(Service service) {
		services.add(service);
		services.get(counter).setId(counter + 1);
		counter++;
	}
}