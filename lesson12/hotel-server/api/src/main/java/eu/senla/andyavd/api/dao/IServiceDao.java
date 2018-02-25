package eu.senla.andyavd.api.dao;

import java.sql.Connection;

import eu.senla.andyavd.beans.Service;

public interface IServiceDao extends IGenericDao<Service> {
	void changeServicePrice(Connection connection, Double daily_price, int id) throws Exception;
}