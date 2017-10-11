package com.andyavd;

public class ChassisLineStep implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Chassis building");
		return new Chassis();
	}
}
