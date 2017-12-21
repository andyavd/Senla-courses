package eu.senla.andyavd.hoteladministrator.managers;

import java.util.Arrays;
import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.api.IServiceManager;
import eu.senla.andyavd.hoteladministrator.entities.Entity;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.storages.ServicesStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class ServiceManager implements IServiceManager {

	ServicesStorage servicesStorage = new ServicesStorage();

	private final static String path = Path.SERVICE_STORAGE_PATH.getPath();
	
	private Service[] castEntitiesArray(Entity[] entities) {
		Service[] serviceArray = Arrays.copyOf(entities, entities.length, Service[].class);
		return serviceArray;
	}
	
	@Override
	public void addService(Service service) {
		servicesStorage.addService(service);
	}

	@Override
	public Service[] getServices() {
		return servicesStorage.getServices();
	}

	@Override
	public Service[] sortServices(Comparator<Service> comparator) {
		Service[] sortedServices = servicesStorage.getServices();
		Arrays.sort(sortedServices, comparator);
		return sortedServices;
	}

	public void saveToFile() {
		String[] stringArray = Arrays.copyOf(ArrayWorker.arrayToString(servicesStorage.getServices()), ArrayWorker.getArraySize(servicesStorage.getServices()));
		FileWriter.writeToFile(stringArray, path); 
	}

	public String[] loadFromFile() {
		return FileReader.readFromFile(path);
	}
}
