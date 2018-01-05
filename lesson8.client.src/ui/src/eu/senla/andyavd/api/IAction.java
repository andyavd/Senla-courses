package ui.src.eu.senla.andyavd.api;

import hotel.src.eu.senla.andyavd.server.ServerWorker;

public interface IAction {
	public void execute(ServerWorker serverWorker);
}