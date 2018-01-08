package eu.senla.andyavd.enums;

public enum RoomHistoryStatus {
	CHECKIN("CheckIn"), CHECKOUT("CheckOut");
	
	private String name;

	RoomHistoryStatus(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}