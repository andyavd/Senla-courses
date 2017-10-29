package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.Service;

public class ServicesStorage {

	private Service[] services = new Service[4];

	public void addService(Service service) {
		for (int i = 0; i < services.length; i++) {
			
//			 if(i == rooms.length - 1) {
//			 Room[] stretchedArray = new Room[rooms.length * 2];
//			 System.arraycopy(rooms, 0, stretchedArray, 0, rooms.length - 1);
//			 rooms = stretchedArray;
//			 }
			if (services[i] == null) {
				services[i] = service;
				services[i].setId(i + 1);
				break;
			}
		}
	}

	
	public Service[] getServices() {
		return services;
	}

}