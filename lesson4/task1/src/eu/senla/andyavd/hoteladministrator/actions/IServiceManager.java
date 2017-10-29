package eu.senla.andyavd.hoteladministrator.actions;

import eu.senla.andyavd.hoteladministrator.entities.Service;

public interface IServiceManager {
	
	public void addService(Service service);
	public void showServices();
	public void sortServicesByName();
	public void sortServicesByPrice();
}