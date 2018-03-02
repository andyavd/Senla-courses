package eu.senla.andyavd.hotel.managers;

import org.hibernate.Session;
import eu.senla.andyavd.hotel.dbconnector.DataBaseConnector;

public abstract class AManager {
	protected Session session = DataBaseConnector.getSession();
}
