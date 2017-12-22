package lesson6.hotel.src.eu.senla.andyavd.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import lesson6.hotel.src.eu.senla.andyavd.entities.AEntity;
import lesson6.hotel.src.eu.senla.andyavd.entities.Room;
import lesson6.hotel.src.eu.senla.andyavd.entities.RoomHistory;
import lesson6.hotel.src.eu.senla.andyavd.entities.Service;
import lesson6.hotel.src.eu.senla.andyavd.entities.Visitor;
import lesson6.hotel.src.eu.senla.andyavd.storages.RoomHistoriesStorage;
import lesson6.hotel.src.eu.senla.andyavd.storages.RoomsStorage;
import lesson6.hotel.src.eu.senla.andyavd.storages.ServicesStorage;
import lesson6.hotel.src.eu.senla.andyavd.storages.VisitorsStorage;
import lesson6.properties.src.eu.senla.andyavd.properties.Settings;

public class SerializationUtil {

	private static final Logger logger = Logger.getLogger(SerializationUtil.class);
	private static final String SAVELOAD_PROPERTY = Settings.getInstance().getProperty("saveload");

	public static void serialize(List<Room> rooms, List<Service> services, List<Visitor> visitors, List<RoomHistory> histories) {

		List<List<? extends AEntity>> entities = new ArrayList<>();
		
		try {

			FileOutputStream fileOutputStream = new FileOutputStream(
					SerializationUtil.class.getClassLoader().getResource(SAVELOAD_PROPERTY).getFile());
			ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
			
			entities.add(rooms);
			entities.add(services);
			entities.add(visitors);
			entities.add(histories);
			
			objectOutputStream.writeObject(entities);
			objectOutputStream.close();
			fileOutputStream.close();

		} catch (FileNotFoundException e) {
			logger.error("No such file!", e);
		} catch (IOException e) {
			logger.error("Fail to save the Hotel state!", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void deserialize() {

		try {
			FileInputStream fileInputStream = new FileInputStream(
					SerializationUtil.class.getClassLoader().getResource(SAVELOAD_PROPERTY).getFile());
			ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

			List<List<? extends AEntity>> entities = (List<List<? extends AEntity>>) objectInputStream.readObject();
			
			RoomsStorage.getInstance().setRooms((List<Room>) entities.get(0));
			ServicesStorage.getInstance().setServices((List<Service>) entities.get(1));
			VisitorsStorage.getInstance().setVisitors((List<Visitor>) entities.get(2));
			RoomHistoriesStorage.getInstance().setHistories(((List<RoomHistory>) entities.get(3)));
			
			objectInputStream.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
