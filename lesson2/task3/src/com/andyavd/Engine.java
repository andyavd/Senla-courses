package com.andyavd;

public class Engine implements IProductPart {
	
	private int power;
	
	public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
	
	public Engine(int power) {
		this.power = power;
		System.out.println("Building Engine");
	}

}
