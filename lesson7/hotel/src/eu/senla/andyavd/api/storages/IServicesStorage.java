package eu.senla.andyavd.api.storages;

import java.util.List;

import eu.senla.andyavd.entities.Service;

public interface IServicesStorage {

	public List<Service> getServices();
	
	public void setServices(List <Service> services);

	public void addService(Service service);

	public Service getServiceById(Integer id);

	void deleteService(Service service);

}
