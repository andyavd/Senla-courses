package lesson7.ui.src.eu.senla.andyavd.utils;

public enum MenuTypes {
	ACTION("Select the action"),
	MAIN_MENU("Main menu");
	
	private String path;

	MenuTypes(String path) {
        this.path = path;
    }

    public String getConstant() {
        return path;
    }
}