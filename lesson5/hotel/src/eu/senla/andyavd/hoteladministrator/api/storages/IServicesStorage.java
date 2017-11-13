package eu.senla.andyavd.hoteladministrator.api.storages;

import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Service;

public interface IServicesStorage {

	public List<AEntity> getServices();

	public void addService(Service service);

	public Service getServiceById(Integer id);

}
