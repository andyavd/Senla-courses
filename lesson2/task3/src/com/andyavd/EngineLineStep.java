package com.andyavd;

public class EngineLineStep implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		return new Engine(150);
	}

}
