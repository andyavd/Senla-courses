package eu.senla.andyavd.hoteladministrator.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.managers.IServiceManager;
import eu.senla.andyavd.hoteladministrator.api.storages.IServicesStorage;
import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.storages.ServicesStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class ServiceManager implements IServiceManager {

	private IServicesStorage servicesStorage = new ServicesStorage();

	private final static String path = Path.SERVICE_STORAGE_PATH.getPath();
	
	@Override
	public void addService(Service service) {
		servicesStorage.addService(service);
	}

	@Override
	public List<AEntity> getServices() {
		return servicesStorage.getServices();
	}

	@Override
	public List<AEntity> sortServices(Comparator<AEntity> comparator) {
		List<AEntity> sortedServices = servicesStorage.getServices();
		Collections.sort(sortedServices, comparator);
		return sortedServices;
	}

	@Override
	public Service getServiceById(Integer id) {
		return servicesStorage.getServiceById(id);
	}
	
	@Override
	public void saveToFile() {
		FileWriter.writeToFile(ArrayWorker.arrayToString(servicesStorage.getServices()), path); 
	}

	@Override
	public String[] loadFromFile() {
		return FileReader.readFromFile(path);
	}
}