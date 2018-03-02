package eu.senla.andyavd.hotelui.ui.api;

import eu.senla.andyavd.hotel.utils.client.TransactionWorker;

public interface IAction {
	public void execute(TransactionWorker serverWorker);
}