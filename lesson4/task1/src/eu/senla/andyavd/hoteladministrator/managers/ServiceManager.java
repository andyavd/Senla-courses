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

	ServicesStorage ss = new ServicesStorage();
	ArrayWorker aw = new ArrayWorker();

	private final static String path = Path.SERVICE_STORAGE_PATH.getPath();
	
	private Service[] castEntitiesArray(Entity[] entities) {
		Service[] serviceArray = Arrays.copyOf(entities, entities.length, Service[].class);
		return serviceArray;
	}
	
	@Override
	public void addService(Service service) {
		ss.addService(service);
	}

	@Override
	public Service[] getServices() {
		return ss.getServices();
	}

	/* ======================sorting============================ */

	@Override
	public void sortServices(Comparator comparator) {
		Arrays.sort(ss.getServices(), comparator);
	}

	public void saveToFile() {
		String[] stringArray = Arrays.copyOf(ArrayWorker.arrayToString(ss.getServices()), ArrayWorker.getArraySize(ss.getServices()));
		FileWriter.writeToFile(stringArray, path); 
	}

	public String[] loadFromFile() {
		return FileReader.readFromFile(path);
	}
}
