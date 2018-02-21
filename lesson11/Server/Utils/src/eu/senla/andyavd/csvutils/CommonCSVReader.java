package eu.senla.andyavd.csvutils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import eu.senla.andyavd.AEntity;
import eu.senla.andyavd.Settings;
import eu.senla.andyavd.annotations.CsvEntity;

public class CommonCSVReader {
	
	private static final Logger logger = Logger.getLogger(CommonCSVReader.class);
	private static final String DATA_STORAGE = Settings.getCustomProperties().getDataStorage();
	
	public static List<? extends AEntity> readFromFile(Class<? extends AEntity> entityClass) {

        List<AEntity> entities = new ArrayList<>();

        CsvEntity annotation = entityClass.getAnnotation(CsvEntity.class);

        List<String> stringToObjects = new ArrayList<>();
        String line = null;

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA_STORAGE + annotation.filename()))) {
            while ((line = bufferedReader.readLine()) != null) {
                stringToObjects.add(line);
            }
        } catch (FileNotFoundException e) {
        		logger.error("File not found!", e);
        } catch (IOException e) {
        		logger.error("IOException!", e);
        }

        if (stringToObjects.size() != 0) {
            for (String objectLine : stringToObjects) {
                String[] entityParameters = objectLine.trim().split(annotation.valuesSeparator());
                entities.add(CommonCSVConvertor.convert(entityClass, entityParameters));
            }
        }

        return entities;
    }
}
