package eu.senla.andyavd.csvutils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.AEntity;
import eu.senla.andyavd.DateFormatter;

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
			case "Date":
				Date date = DateFormatter.dateFromString(str);
				objects.add(date);
				break;
			}
		} else {
			objects.add(null);
		}
		return objects;
	}
}