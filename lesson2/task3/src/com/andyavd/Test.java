package com.andyavd;

public class Test {

	public static void main(String[] args) {
		
		BodyLineStep bodyLineStep = new BodyLineStep();
		ChassisLineStep chassisLineStep = new ChassisLineStep();
		EngineLineStep engineLineStep = new EngineLineStep();

		IAssemblyLine iAssemblyLine = new AssemblyLine((Body) bodyLineStep.buildProductPart(),
				(Chassis) chassisLineStep.buildProductPart(), (Engine) engineLineStep.buildProductPart());
		iAssemblyLine.assembleProduct(new Product());
		System.out.println("Job seems to be done!");
	}
}
