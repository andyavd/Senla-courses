package com.andyavd;

public class BodyLineStep implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Body building");
		return new Body();
	}
}
