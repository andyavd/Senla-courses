package eu.senla.andyavd.enums;

public enum RoomStatus {
	EMPTY("Empty"), OCCUPIED("Occupied"), SERVICED("Serviced");
	
	private String name;

	RoomStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
