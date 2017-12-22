package lesson7.hotel.src.eu.senla.andyavd.enums;

public enum Path {
	
	ROOM_STORAGE_PATH ("data/room.txt"),
	VISITOR_STORAGE_PATH("data/visitor.txt"),
	SERVICE_STORAGE_PATH("data/service.txt"),
	LOG_STORAGE_PATH("data/log.txt");
	
	private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }
}
