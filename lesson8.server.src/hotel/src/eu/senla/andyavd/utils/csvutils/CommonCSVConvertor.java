package hotel.src.eu.senla.andyavd.utils.csvutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import hotel.src.eu.senla.andyavd.entities.AEntity;
import hotel.src.eu.senla.andyavd.entities.Room;
import hotel.src.eu.senla.andyavd.enums.RoomHistoryStatus;
import hotel.src.eu.senla.andyavd.enums.RoomStars;
import hotel.src.eu.senla.andyavd.enums.RoomStatus;
import hotel.src.eu.senla.andyavd.view.HotelManager;

public class CommonCSVConvertor {
	private static final Logger logger = Logger.getLogger(CommonCSVConvertor.class.getName());

	public static String convertEntityToString(Object object, String keyField) {
		try {
			if (object != null) {
				switch (object.getClass().getSimpleName().toString()) {

				case "ArrayList":
					@SuppressWarnings("unchecked") List<? extends AEntity> entities = (List<? extends AEntity>) object;
					StringBuilder stringBuilder = new StringBuilder();
					for (AEntity entity : entities) {
						Field entityField = null;
						entityField = entity.getClass().getDeclaredField(keyField);
						entityField.setAccessible(true);
						stringBuilder.append(",").append(entityField.get(entity));
					}
					return String.valueOf(stringBuilder.deleteCharAt(0));
				}
			}
		} catch (IllegalAccessException e) {
			logger.error("Illegal Access!", e);
		} catch (NoSuchFieldException e) {
			logger.error("No Such Field!", e);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unused", "unchecked" })
	public static AEntity convert(Class<? extends AEntity> entityClass, String[] lines) {
		try {
			Constructor constructor = null;

			for (Constructor constr : entityClass.getConstructors()) {
				if (constr.getParameterCount() == lines.length)
					constructor = constr;
			}

			if (constructor != null) {
				Class[] constructorParamsTypes = constructor.getParameterTypes();
				Field[] entityFields = entityClass.getDeclaredFields();
				List params = new ArrayList();
				for (int i = 0; i < lines.length; i++) {
					List parsedResult = parse(constructorParamsTypes[i], lines[i]);
					if (constructorParamsTypes[i].getSimpleName().equalsIgnoreCase("List")) {
						params.add(parsedResult);
					} else
						params.add(parsedResult.get(0));
				}
				return (AEntity) constructor.newInstance(params.toArray());
			}
		} catch (IllegalAccessException e) {
			logger.error("Illegal Access!", e);
		} catch (InstantiationException e) {
			logger.error("Instantiation!", e);
		} catch (InvocationTargetException e) {
			logger.error("Invocation Target!", e);
		}
		return null;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private static List parse(Class cl, String str) {
		List objects = new ArrayList();

		if (!str.equalsIgnoreCase("null")) {
			switch (cl.getSimpleName()) {
			case "String":
				objects.add(str);
				break;
			case "int":
				objects.add(new Integer(Integer.parseInt(str)));
				break;
			case "Integer":
				objects.add(new Integer(Integer.parseInt(str)));
				break;
			case "Double":
				objects.add(new Double(Double.parseDouble(str)));
				break;
			case "LocalDate":
				LocalDate localDate = LocalDate.parse(str);
				objects.add(localDate);
				break;
			case "RoomStars":
				for (RoomStars star : RoomStars.values()) {
					if (star.toString().equalsIgnoreCase(str)) {
						objects.add(star);
					}
				}
				break;
			case "RoomStatus":
				for (RoomStatus status : RoomStatus.values()) {
					if (status.toString().equalsIgnoreCase(str)) {
						objects.add(status);
					}
				}
				break;
			case "RoomHistoryStatus":
				for (RoomHistoryStatus status : RoomHistoryStatus.values()) {
					if (status.toString().equalsIgnoreCase(str)) {
						objects.add(status);
					}
				}
				break;
			case "List":
				String[] lines = str.trim().split(",");
				List<Room> rooms = HotelManager.getInstance().getRooms();
				for (Room room : rooms) {
					for (String id : lines) {
						if (String.valueOf(room.getId()) == id) {
							objects.add(room);
						}
					}
				}
				break;
			}
		} else {
			objects.add(null);
		}
		return objects;
	}

}
