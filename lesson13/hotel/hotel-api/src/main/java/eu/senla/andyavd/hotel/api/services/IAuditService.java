package eu.senla.andyavd.hotel.api.services;

import java.util.List;

import eu.senla.andyavd.hotel.entity.beans.Audit;
import eu.senla.andyavd.hotel.entity.enums.SortType;

public interface IAuditService {

	void addAudit(Audit audit) throws Exception;

	List<Audit> getAudits(SortType type) throws Exception;
}
