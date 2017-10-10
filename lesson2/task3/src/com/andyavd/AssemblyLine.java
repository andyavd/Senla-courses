package com.andyavd;

public class AssemblyLine implements IAssemblyLine {
	
	@Override
	public IProduct assembleProduct(IProduct iProduct) {
		
		System.out.println("Start of Product Creating...");
		
		Body body = (Body) new BodyLineStep().buildProductPart();
        Chassis chassis = (Chassis) new ChassisLineStep().buildProductPart();
        Engine engine = (Engine) new EngineLineStep().buildProductPart();
		
        iProduct.installFirstPart(body);
        iProduct.installSecondPart(chassis);
        iProduct.installThirdPart(engine);
        
	Product product = new Product(body, chassis, engine);
		
        System.out.println("A new Product was successfully created: " + body.getName() + " - " + body.getBodyType() + ", " + chassis.getName() + " - " + chassis.getDrive() + ", " + engine.getName() + " - " + engine.getPower() + " bhp.");
        
		return iProduct;
	}

}
