package com.andyavd;

public class ChassisLineStep implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		return new Chassis("AWD");
	}

}
