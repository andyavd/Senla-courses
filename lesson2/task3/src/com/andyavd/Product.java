package com.andyavd;

public class Product implements IProduct {

	private Body body;
	private Chassis chassis;
	private Engine engine;
	
	@Override
	public void installFirstPart(IProductPart firstProductPart) {
		System.out.println("Installing the first part");
	}

	@Override
	public void installSecondPart(IProductPart secondProductPart) {
		System.out.println("Installing the second part");
	}

	@Override
	public void installThirdPart(IProductPart thirdProductPart) {
		System.out.println("Installing the third part");
	}

	public Body getBody() {
		return body;
	}

	public void setBody(Body body) {
		this.body = body;
	}

	public Chassis getChassis() {
		return chassis;
	}

	public void setChassis(Chassis chassis) {
		this.chassis = chassis;
	}

	public Engine getEngine() {
		return engine;
	}

	public void setEngine(Engine engine) {
		this.engine = engine;
	}
	
	public Product(){}
	
	public Product(Body body, Chassis chassis, Engine engine) {
        this.body = body;
        this.chassis = chassis;
        this.engine = engine;
        System.out.println("Creating the Product");
    }

}
