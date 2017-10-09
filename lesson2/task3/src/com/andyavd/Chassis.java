package com.andyavd;

public class Chassis extends CarPart implements IProductPart {
	
	private String drive;

	public String getDrive() {
        return drive;
    }

    public void setDrive(String drive) {
        this.drive = drive;
    }
    
    public Chassis(String name, String drive) {
		super(name);
		this.drive = drive;
		System.out.println("Building Chassis");
	}
}
