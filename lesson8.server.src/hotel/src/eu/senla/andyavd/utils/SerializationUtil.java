package hotel.src.eu.senla.andyavd.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.AEntity;
import hotel.src.eu.senla.andyavd.entities.Room;
import hotel.src.eu.senla.andyavd.entities.RoomHistory;
import hotel.src.eu.senla.andyavd.entities.Service;
import hotel.src.eu.senla.andyavd.entities.Visitor;
import hotel.src.eu.senla.andyavd.storages.RoomHistoriesStorage;
import hotel.src.eu.senla.andyavd.storages.RoomsStorage;
import hotel.src.eu.senla.andyavd.storages.ServicesStorage;
import hotel.src.eu.senla.andyavd.storages.VisitorsStorage;
import properties.src.eu.senla.andyavd.properties.Settings;

public class SerializationUtil {

	private static final Logger logger = Logger.getLogger(SerializationUtil.class);
	private static final String SAVELOAD_PROPERTY = Settings.getInstance().getProperty("saveload");

	public static void serialize(List<Room> rooms, List<Service> services, List<Visitor> visitors, List<RoomHistory> histories) {

		List<List<? extends AEntity>> entities = new ArrayList<>();
		
		try {

			ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(SAVELOAD_PROPERTY));
			
			entities.add(rooms);
			entities.add(services);
			entities.add(visitors);
			entities.add(histories);
			
			objectOutputStream.writeObject(entities);
			objectOutputStream.close();

		} catch (FileNotFoundException e) {
			logger.error("No such file!", e);
		} catch (IOException e) {
			logger.error("Fail to save the Hotel state!", e);
		}
	}

	@SuppressWarnings("unchecked")
	public static void deserialize() {

		try {
			ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(SAVELOAD_PROPERTY));

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
