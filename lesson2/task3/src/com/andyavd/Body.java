package com.andyavd;

public class Body implements IProductPart {
	
	private String bodyType;
	
	public String getBodyType() {
        return bodyType;
    }

    public void setBodyType(String bodyType) {
        this.bodyType = bodyType;
    }
    
    public Body(String bodyType) {
		this.bodyType = bodyType;
		System.out.println("Building Body");
	}
}
