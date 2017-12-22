package eu.senla.andyavd.properties;

import java.util.Properties;

public class CustomProperties {
	
	private boolean status;
    private Integer count;
    private String saveload;
    private String instancepath;
    private String roomHistoriesPath;
    private String roomsPath;
    private String servicesPath;
    private String visitorsPath;
    
    private static CustomProperties customProperties;
    
    public static CustomProperties getInstance() {
		if (customProperties == null) {
			customProperties = new CustomProperties();
			customProperties.init();
		}
		return customProperties;
	}
    
    public void init() {
		Properties properties = Settings.initialize();
		status = Boolean.parseBoolean(properties.getProperty("status"));
		count = Integer.parseInt(properties.getProperty("count"));
		saveload = properties.getProperty("saveload");
		instancepath = properties.getProperty("instancepath");
		roomHistoriesPath = properties.getProperty("roomHistoriesPath");
		roomsPath = properties.getProperty("roomsPath");
		servicesPath = properties.getProperty("servicesPath");
		visitorsPath = properties.getProperty("visitorsPath");
		
		
	}

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
        return instancepath;
    }

    public void setEntityPath(String instancepath) {
        this.instancepath = instancepath;
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
