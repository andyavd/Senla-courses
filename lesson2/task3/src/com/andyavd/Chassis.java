package com.andyavd;

public class Chassis implements IProductPart {
	
	private String drive;

	public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }
    
    public Chassis(String drive) {
		this.drive = drive;
		System.out.println("Building Chassis");
	}
}
