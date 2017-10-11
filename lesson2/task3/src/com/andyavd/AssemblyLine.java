package com.andyavd;

public class AssemblyLine implements IAssemblyLine {
	
	private Body body;
	private Chassis chassis;
	private Engine engine;
	
	public AssemblyLine(Body body, Chassis chassis, Engine engine){
        	this.body = body;
        	this.chassis = chassis;
		this.engine = engine;
    	}
	
	@Override
	public IProduct assembleProduct(IProduct iProduct) {
		
		System.out.println("Start of Product Creating...");
		
        	iProduct.installFirstPart(body);
        	iProduct.installSecondPart(chassis);
        	iProduct.installThirdPart(engine);
        
        	System.out.println("A new Product was successfully created!");
        
		return iProduct;
	}
}
