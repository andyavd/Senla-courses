package lesson8.server.hotel.src.eu.senla.andyavd.utils.exceptions;

@SuppressWarnings("serial")
public class EmptyRoomException extends Exception{
	
	public EmptyRoomException(String message) {
		super(message);
	}
}
