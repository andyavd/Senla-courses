package eu.senla.andyavd.hoteladministrator.controllers;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.controllers.IServiceManager;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.storages.ServicesStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class ServiceManager implements IServiceManager {

	FileReader fileReader = new FileReader();
	FileWriter fileWriter = new FileWriter();
	
	private final static String path = Path.SERVICE_STORAGE_PATH.getPath();
	
	@Override
	public void addService(Service service) {
		ServicesStorage.getInstance().addService(service);
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
	
	@Override
	public void saveToFile() {
		fileWriter.writeToFile(ArrayWorker.arrayToString(ServicesStorage.getInstance().getServices()), path); 
	}

	@Override
	public String[] loadFromFile() {
		return fileReader.readFromFile(path);
	}
}
