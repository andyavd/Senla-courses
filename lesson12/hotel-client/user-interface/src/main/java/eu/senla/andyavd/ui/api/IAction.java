package eu.senla.andyavd.ui.api;

import eu.senla.andyavd.server.TransactionWorker;

public interface IAction {
	public void execute(TransactionWorker serverWorker);
}