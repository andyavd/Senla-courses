package eu.senla.andyavd.hoteladministrator.managers;

import java.util.Arrays;

import eu.senla.andyavd.hoteladministrator.actions.IServiceManager;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.storages.ServicesStorage;
import eu.senla.andyavd.hoteladministrator.utils.Printer;
import eu.senla.andyavd.hoteladministrator.utils.sorters.servisesorters.SortingServicesByName;
import eu.senla.andyavd.hoteladministrator.utils.sorters.servisesorters.SortingServicesByPrice;

public class ServiceManager implements IServiceManager {

	ServicesStorage ss = new ServicesStorage();

	@Override
	public void addService(Service service) {
		ss.addService(service);
	}

	@Override
	public void showServices() {
		for (int i = 0; i < ss.getServices().length; i++) {
			if (ss.getServices()[i] != null) {
				Printer.print(ss.getServices()[i].toString());
			}
		}
	}

	/* ======================sorting============================ */

	@Override
	public void sortServicesByName() {
		Arrays.sort(ss.getServices(), new SortingServicesByName());
		for (int i = 0; i < ss.getServices().length; i++) {
			Printer.print(ss.getServices()[i].toString());
		}
	}

	@Override
	public void sortServicesByPrice() {
		Arrays.sort(ss.getServices(), new SortingServicesByPrice());
		for (int i = 0; i < ss.getServices().length; i++) {
			Printer.print(ss.getServices()[i].toString());
		}
	}
}