package eu.senla.andyavd.hotel.dao.dao;

import eu.senla.andyavd.hotel.api.dao.IAuditDao;
import eu.senla.andyavd.hotel.entity.beans.Audit;

public class AuditDao extends GenericDao<Audit> implements IAuditDao {

	protected AuditDao(Class<Audit> classs) {
		super(classs);
	}
}