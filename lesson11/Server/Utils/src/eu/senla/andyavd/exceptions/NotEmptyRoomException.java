package eu.senla.andyavd.exceptions;

@SuppressWarnings("serial")
public class NotEmptyRoomException extends Exception{

	public NotEmptyRoomException(String message) {
		super(message);
	}
}
