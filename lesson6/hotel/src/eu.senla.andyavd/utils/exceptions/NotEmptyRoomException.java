package eu.senla.andyavd.hoteladministrator.utils.exceptions;

@SuppressWarnings("serial")
public class NotEmptyRoomException extends Exception{

	public NotEmptyRoomException(String message) {
		super(message);
	}
}
