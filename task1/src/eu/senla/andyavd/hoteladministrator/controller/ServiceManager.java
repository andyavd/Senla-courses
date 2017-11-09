package eu.senla.andyavd.hoteladministrator.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.api.IServiceManager;
import eu.senla.andyavd.hoteladministrator.enums.Path;
import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.Service;
import eu.senla.andyavd.hoteladministrator.model.storages.ServicesStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;
import eu.senla.andyavd.hoteladministrator.utils.FileReader;
import eu.senla.andyavd.hoteladministrator.utils.FileWriter;

public class ServiceManager implements IServiceManager {

	ServicesStorage ss = new ServicesStorage();
	ArrayWorker aw = new ArrayWorker();

	private final static String path = Path.SERVICE_STORAGE_PATH.getPath();
	
	@Override
	public void addService(Service service) {
		ss.addService(service);
	}

	@Override
	public List<Entity> getServices() {
		return ss.getServices();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void sortServices(Comparator comparator) {
		Collections.sort(ss.getServices(), comparator);
	}

	public void saveToFile() {
		FileWriter.writeToFile(ArrayWorker.arrayToString(ss.getServices()), path); 
	}

	public String[] loadFromFile() {
		return FileReader.readFromFile(path);
	}
}