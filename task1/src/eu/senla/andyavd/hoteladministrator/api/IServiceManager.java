package eu.senla.andyavd.hoteladministrator.api;

import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.model.entities.Entity;
import eu.senla.andyavd.hoteladministrator.model.entities.Service;

public interface IServiceManager {

	public void addService(Service service);

	public List<Entity> getServices();

	@SuppressWarnings("rawtypes")
	public void sortServices(Comparator comparator);
	
}