package eu.senla.andyavd;

import org.apache.log4j.BasicConfigurator;

public class ConnectorTest {

	public static void main(String[] args) {
		BasicConfigurator.configure();
		DataBaseAccess.getInstance().getConnection();
		DataBaseAccess.getInstance().closeConnection();
	}
}