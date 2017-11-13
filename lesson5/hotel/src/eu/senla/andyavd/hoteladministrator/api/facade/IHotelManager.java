package eu.senla.andyavd.hoteladministrator.api.facade;

import java.time.LocalDate;
import java.util.List;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.EmptyRoomException;
import eu.senla.andyavd.hoteladministrator.utils.exceptions.NotEmptyRoomException;

public interface IHotelManager {

	/* ========================Rooms=========================== */

	public void addRoom(Room room);

	public List<AEntity> getRooms();

	public List<AEntity> getEmptyRooms();

	public Integer getEmptyRoomsNumber();

	public String getRoomDetails(Room room);

	public List<AEntity> sortRoomsByCapacity();

	public List<AEntity> sortRoomsByPrice();

	public List<AEntity> sortRoomsByStars();

	public List<AEntity> sortEmptyRoomsByCapacity();

	public List<AEntity> sortEmptyRoomsByPrice();

	public List<AEntity> sortEmptyRoomsByStars();

	public String billVisitor(Visitor visitor);

	public List<AEntity> getEmptyRoomsOnDate(LocalDate date);

	public String changeRoomStatus(Room room);

	public List<String> getLastVisitorsOfRoom(Room room);

	public void changePriceOnRoom(Room room, double dailyPrice);

	public Room getRoomById(Integer id);

	/* ========================Visitors======================== */

	public void addVisitor(Visitor visitor);

	public List<AEntity> getVisitors();

	public void deleteVisitor(Visitor visitor);

	public List<AEntity> sortVisitorsByName();

	public void addServicesToVisitor(Visitor visitor, Service service);

	public List<String> getVisitorServices(Visitor visitor);

	public List<AEntity> sortVisitorServicesByPrice(Visitor visitor);

	public String getTotalVisitorsOnDate(LocalDate date);

	public Visitor getVisitorById(Integer id);

	/* ========================Services======================== */

	public void addService(Service service);

	public List<AEntity> getServices();

	public List<AEntity> sortServicesByName();

	public List<AEntity> sortServicesByPrice();

	public void changePriceOnService(Service service, double dailyPrice);

	public Service getServiceById(Integer id);

	/* ========================Process========================= */

	public String checkInVisitor(Visitor visitor, Room room, LocalDate checkInDate, LocalDate checkOutDate) throws NotEmptyRoomException;

	public String checkOutVisitor(Visitor visitor, Room room) throws EmptyRoomException;
	
	public void saveToFile();
	
	public void loadFromFile();

}
