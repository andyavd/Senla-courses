package eu.senla.andyavd.hoteladministrator.api.managers;

import java.util.Comparator;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Service;

public interface IServiceManager {

	public void addService(Service service);

	public List<AEntity> getServices();

	public List<AEntity> sortServices(Comparator<AEntity> comparator);
	
	public Service getServiceById(Integer id);

	public void saveToFile();

	public String[] loadFromFile();
	
}