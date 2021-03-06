package eu.senla.andyavd.storages;

import java.util.ArrayList;
import java.util.List;

import eu.senla.andyavd.annotations.Storage;
import eu.senla.andyavd.api.storages.IVisitorsStorage;
import eu.senla.andyavd.entities.RoomHistory;
import eu.senla.andyavd.entities.Visitor;
import eu.senla.andyavd.utils.csvutils.CommonCSVReader;
import eu.senla.andyavd.utils.csvutils.CommonCSVWriter;

@Storage
public class VisitorsStorage implements IVisitorsStorage {

	private List<Visitor> visitors = new ArrayList<Visitor>();

	private static VisitorsStorage visitorsStorage;

	public static VisitorsStorage getInstance() {
		if (visitorsStorage == null) {
			visitorsStorage = new VisitorsStorage();
		}
		return visitorsStorage;
	}

	@Override
	public List<Visitor> getVisitors() {
		return visitors;
	}

	@Override
	public void setVisitors(List<Visitor> visitors) {
		this.visitors = visitors;
	}

	@Override
	public void addVisitor(Visitor visitor) {
		visitors.add(visitor);
		visitors.get(visitors.size() - 1).setId(visitors.size());
	}

	@Override
	public void updateVisitor(Visitor visitor, RoomHistory history) {
		visitor.setHistory(history);
	}

	@Override
	public void deleteVisitor(Visitor visitor) {
		for (int i = 0; i < visitors.size(); i++) {
			if (visitors.get(i) == visitor) {
				visitors.remove(i);
			}
		}
	}

	@Override
	public Visitor getVisitorById(Integer id) {

		Visitor visitor = null;

		for (int i = 0; i < visitors.size(); i++) {
			if (visitors.get(i).getId() == id) {
				visitor = visitors.get(i);
			}
		}
		return visitor;
	}
	
	@Override
	public void importFromCsv() {
		@SuppressWarnings("unchecked")
		List<Visitor> importedVisitors = (List<Visitor>) CommonCSVReader.readFromFile(Visitor.class);
		for (Visitor visitor : importedVisitors) {
			if (!visitors.contains(visitor)) {
				visitors.add(visitor);
			}
		}
	}

	@Override
	public void exportToCsv() {
		CommonCSVWriter.writeToFile(visitors);
	}
}
