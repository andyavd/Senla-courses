package lesson8.server.hotel.src.eu.senla.andyavd.utils.exceptions;

@SuppressWarnings("serial")
public class NotEmptyRoomException extends Exception{

	public NotEmptyRoomException(String message) {
		super(message);
	}
}
