package eu.senla.andyavd.hoteladministrator.enums;

public enum Path {
	ROOM_STORAGE_PATH ("E:\\Java\\eclipse-workspace\\eu.senla.andyavd.hotel\\data\\room.txt"),
	VISITOR_STORAGE_PATH("E:\\Java\\eclipse-workspace\\eu.senla.andyavd.hotel\\data\\visitor.txt"),
	SERVICE_STORAGE_PATH("E:\\Java\\eclipse-workspace\\eu.senla.andyavd.hotel\\data\\service.txt");
	
	private String path;

    Path(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
