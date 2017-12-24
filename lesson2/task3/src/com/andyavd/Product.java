package com.andyavd;

public class Product implements IProduct {
	private Body body;
	private Chassis chassis;
	private Engine engine;
	
	@Override
	public void installFirstPart(IProductPart firstProductPart) {
		body = (Body) firstProductPart;
		System.out.println("Installing body");
	}

	@Override
	public void installSecondPart(IProductPart secondProductPart) {
		chassis = (Chassis) secondProductPart;
		System.out.println("Installing chassis");
	}

	@Override
	public void installThirdPart(IProductPart thirdProductPart) {
		engine = (Engine) thirdProductPart;
		System.out.println("Installing engine");
	}
}
