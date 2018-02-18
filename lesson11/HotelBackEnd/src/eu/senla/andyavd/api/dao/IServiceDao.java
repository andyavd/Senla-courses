package eu.senla.andyavd.api.dao;

import java.sql.Connection;

import eu.senla.andyavd.Service;

public interface IServiceDao extends IAEntityDao<Service>{
	void changeServicePrice(Connection connection, Double daily_price, int id);
}