package com.andyavd;

public class AssemblyLine implements IAssemblyLine {
	
	public AssemblyLine(){
        assembleProduct(new Product());
    }
	
	@Override
	public IProduct assembleProduct(IProduct iProduct) {
		
		System.out.println("Start of Product Creating...");
		
		Body body = (Body) new BodyLineStep().buildProductPart();
        Chassis chassis = (Chassis) new ChassisLineStep().buildProductPart();
        Engine engine = (Engine) new EngineLineStep().buildProductPart();
		
        iProduct.installFirstPart(body);
        iProduct.installSecondPart(chassis);
        iProduct.installThirdPart(engine);
        
        System.out.println("A new Product was successfully created");
        
		return iProduct;
	}

}
