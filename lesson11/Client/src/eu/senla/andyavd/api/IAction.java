package eu.senla.andyavd.api;

import eu.senla.andyavd.server.TransactionWorker;

public interface IAction {
	public void execute(TransactionWorker serverWorker);
}