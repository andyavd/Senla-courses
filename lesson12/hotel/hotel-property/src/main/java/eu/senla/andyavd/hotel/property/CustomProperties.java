package eu.senla.andyavd.hotel.property;

public class CustomProperties {
	
	private boolean status;
	private Integer count;
	private String saveload;
	private String instancepath;
	private String roomHistoriesPath;
	private String roomsPath;
	private String servicesPath;
	private String visitorsPath;
	private String datastorage;
	private String driver;
	private String url;
	private String username;
	private String password;

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

	public String getInstancePath() {
		return instancepath;
	}

	public void setInstancePath(String instancepath) {
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

	public String getDataStorage() {
		return datastorage;
	}

	public void setDataStorage(String datastorage) {
		this.datastorage = datastorage;
	}

	public String getDriver() {
		return driver;
	}

	public void setDriver(String driver) {
		this.driver = driver;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}