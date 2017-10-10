package com.andyavd;

public class Test {

	public static void main(String[] args) {
		
		AssemblyLine assemblyLine = new AssemblyLine();
		assemblyLine.assembleProduct(new Product());
		System.out.println("Job seems to be done!");
	}

}
