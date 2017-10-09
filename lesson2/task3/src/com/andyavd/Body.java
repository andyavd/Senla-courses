package com.andyavd;

public class Body extends CarPart implements IProductPart {
	
	private String bodyType;
	
	public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
    
    public Body(String name, String bodyType) {
		super(name);
		this.bodyType = bodyType;
		System.out.println("Building Body");
	}
}
