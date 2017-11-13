package eu.senla.andyavd.ui.entityactions.service;

import java.util.Scanner;

import eu.senla.andyavd.hoteladministrator.entities.Service;
import eu.senla.andyavd.hoteladministrator.view.HotelManager;
import eu.senla.andyavd.ui.api.IAction;
import eu.senla.andyavd.ui.utils.InputReader;

public class AddService implements IAction{

	@Override
	public void execute() {

		Scanner scanner = new Scanner(System.in);
		
		String name = InputReader.getStringInput(scanner, "Input the Service name...");
		Double dailyPrice = InputReader.getDoubleInput(scanner, "Input the Room daily price...");
		
		HotelManager.getInstance().addService(new Service(name, dailyPrice));
		
	}
}