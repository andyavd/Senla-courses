package eu.senla.andyavd.hoteladministrator.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.hoteladministrator.entities.AEntity;
import eu.senla.andyavd.hoteladministrator.entities.Room;
import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;
import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.entities.Visitor;
import eu.senla.andyavd.hoteladministrator.storages.RoomHistoriesStorage;
import eu.senla.andyavd.hoteladministrator.storages.RoomsStorage;
import eu.senla.andyavd.hoteladministrator.storages.ServicesStorage;
import eu.senla.andyavd.hoteladministrator.storages.VisitorsStorage;
import eu.senla.andyavd.properties.Settings;

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
