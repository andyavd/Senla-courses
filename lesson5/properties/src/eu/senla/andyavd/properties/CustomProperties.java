package eu.senla.andyavd.properties;

public class CustomProperties {
	
	private boolean status;
    private Integer count;
    private String saveload;
    private String entityPath;
    private String roomHistoriesPath;
    private String roomsPath;
    private String servicesPath;
    private String visitorsPath;
    

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public String getSaveLoadPath() {
        return saveload;
    }

    public void setSaveLoadPath(String saveload) {
        this.saveload = saveload;
    }

    public String getEntityPath() {
        return entityPath;
    }

    public void setEntityPath(String entitypath) {
        this.entityPath = entitypath;
    }
    
    public String getRoomHistoriesPath() {
        return roomHistoriesPath;
    }

    public void setRoomHistoriesPath(String roomHistoriesPath) {
        this.roomHistoriesPath = roomHistoriesPath;
    }

	public String getRoomsPath() {
		return roomsPath;
	}

	public void setRoomsPath(String roomsPath) {
		this.roomsPath = roomsPath;
	}

	public String getServicesPath() {
		return servicesPath;
	}

	public void setServicesPath(String servicesPath) {
		this.servicesPath = servicesPath;
	}

	public String getVisitorsPath() {
		return visitorsPath;
	}

	public void setVisitorsPath(String visitorsPath) {
		this.visitorsPath = visitorsPath;
	}
}
