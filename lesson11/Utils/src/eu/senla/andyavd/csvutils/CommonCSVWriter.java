package eu.senla.andyavd.csvutils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import eu.senla.andyavd.AEntity;
import eu.senla.andyavd.Settings;
import eu.senla.andyavd.annotations.CsvEntity;
import eu.senla.andyavd.annotations.CsvProperty;
import eu.senla.andyavd.enums.PropertyType;

public class CommonCSVWriter {

	private static final Logger logger = Logger.getLogger(CommonCSVWriter.class);
	private static final String DATA_STORAGE = Settings.getCustomProperties().getDataStorage();
	
	public static void writeToFile(List<? extends AEntity> entities) {
        if (entities.size() != 0) {
            Class<? extends AEntity> entityClass = entities.get(0).getClass();
            if (entityClass.isAnnotationPresent(CsvEntity.class)) {
                CsvEntity csvEntityAnnotation = entityClass.getAnnotation(CsvEntity.class);
                File file = new File(DATA_STORAGE + csvEntityAnnotation.filename());
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, false))) {
                    if (!file.exists()) {
                        file.createNewFile();
                    }
                    Field[] fields = entityClass.getDeclaredFields();
                    Map<Integer, String> sortedFields = new TreeMap<>();
                    StringBuilder stringBuilder = new StringBuilder();
                    for (AEntity entity : entities) {
                        for (Field field : fields) {
                            if (field.isAnnotationPresent(CsvProperty.class)) {
                                field.setAccessible(true);
                                if (field.getAnnotation(CsvProperty.class).propertyType() == PropertyType.CompositeProperty) {
                                    sortedFields.put(field.getAnnotation(CsvProperty.class).columnNumber(), CommonCSVConvertor.convertEntityToString(field.get(entity), field.getAnnotation(CsvProperty.class).keyField()));
                                } else {
                                    sortedFields.put(field.getAnnotation(CsvProperty.class).columnNumber(), String.valueOf(field.get(entity)));
                                }
                            }
                        }
                        for (Map.Entry<Integer, String> field : sortedFields.entrySet()) {
                            stringBuilder.append(csvEntityAnnotation.valuesSeparator()).append(field.getValue());
                        }
                        stringBuilder.deleteCharAt(0);
                        bufferedWriter.write(String.valueOf(stringBuilder) + "\n");
                        sortedFields.clear();
                        stringBuilder.delete(0, stringBuilder.length());
                    }
                } catch (IllegalAccessException e) {
                	logger.error("Illegal Access Exception " + e);
                } catch (IOException e) {
                	logger.error("IOException " + e);
                }
            }
        }
    }
}