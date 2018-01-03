package hotel.src.eu.senla.andyavd.enums;

public enum RoomStars {
	STANDARD("Standard"), JUNIOR_SUITE("Juniour_Suite"), LUX("Lux"), PRESIDENT_LUX("President_Lux");
	
	private String name;

	RoomStars(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
