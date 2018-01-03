package hotel.src.eu.senla.andyavd.server;

import java.io.Serializable;

public class Response implements Serializable {

	private static final long serialVersionUID = 1774839161226916836L;

	private Boolean accepted;
	private Object object;

	public Response(Object object) {
		if (object == null) {
			accepted = false;
		} else {
			accepted = true;
			this.object = object;
		}
	}

	public Boolean isAccepted() {
		return accepted;
	}

	public Object getObject() {
		return object;
	}
}
