package com.andyavd;

public class BodyLineStep implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		
		return new Body("Sedan");
	}

}
