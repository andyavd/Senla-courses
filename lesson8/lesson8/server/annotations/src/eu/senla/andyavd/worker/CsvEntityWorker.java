package lesson8.server.annotations.src.eu.senla.andyavd.worker;

import java.io.IOException;

import lesson8.server.annotations.src.eu.senla.andyavd.annotations.CsvEntity;
import lesson8.server.hotel.src.eu.senla.andyavd.utils.csvparsers.writers.RoomHistoryToCSV;
import lesson8.server.hotel.src.eu.senla.andyavd.utils.csvparsers.writers.RoomToCSV;
import lesson8.server.hotel.src.eu.senla.andyavd.utils.csvparsers.writers.ServiceToCSV;
import lesson8.server.hotel.src.eu.senla.andyavd.utils.csvparsers.writers.VisitorToCSV;

public class CsvEntityWorker {

	public CsvEntityWorker() throws IOException {
	}

	public void writeAnnotated(Class<?> entity) {
		if (entity.isAnnotationPresent(CsvEntity.class)) {
			if (entity.getClass().getName().equals("RoomHistory")) {
				RoomHistoryToCSV.writeHistoriesToCSV();
			}
			if (entity.getClass().getName().equals("Room")) {
				RoomToCSV.writeRoomsToCSV();
			}
			if (entity.getClass().getName().equals("Service")) {
				ServiceToCSV.writeServicesToCSV();
			}
			if (entity.getClass().getName().equals("Visitor")) {
				VisitorToCSV.writeVisitorsToCSV();
			}
		}
	}
}
