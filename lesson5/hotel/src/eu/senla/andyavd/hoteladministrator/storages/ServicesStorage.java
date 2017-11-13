package eu.senla.andyavd.hoteladministrator.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.storages.IServicesStorage;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Service;

public class ServicesStorage implements IServicesStorage{

	private List <AEntity> services = new ArrayList<AEntity>();
	private int counter = 0;

	@Override
	public List<AEntity> getServices() {
		return services;
	}

	@Override
	public void addService(Service service) {
		services.add(service);
		services.get(counter).setId(counter + 1);
		counter++;
	}
	
	@Override
	public Service getServiceById(Integer id) {

		Service service = null;

		for (int i = 0; i < services.size(); i++) {
			if (((Service) services.get(i)).getId() == id) {
				service = (Service) services.get(i);
			}
		}
		return service;
	}
}