package eu.senla.andyavd.hoteladministrator.managers;

import java.util.Arrays;
import java.util.Comparator;

import eu.senla.andyavd.hoteladministrator.api.IServiceManager;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.storages.ServicesStorage;
import eu.senla.andyavd.hoteladministrator.utils.ArrayWorker;

public class ServiceManager implements IServiceManager {

	ServicesStorage ss = new ServicesStorage();
	ArrayWorker aw = new ArrayWorker();

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
}
