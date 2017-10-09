package com.andyavd;

public class Engine extends CarPart implements IProductPart {
	
	private int power;
	
	public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }
	
	public Engine(String name, int power) {
		super(name);
		this.power = power;
		System.out.println("Building Engine");
	}

}
