package com.andyavd;

public class EngineLineStep implements ILineStep {

	@Override
	public IProductPart buildProductPart() {
		System.out.println("Engine building");
		return new Engine();
	}
}
