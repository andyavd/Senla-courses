package eu.senla.andyavd.dao;

public class HistoryDao {
	private static HistoryDao historyDao;
	public static HistoryDao getInstance() {
		if (historyDao == null) {
			historyDao = new HistoryDao();
		}
		return historyDao;
	}
	public HistoryDao() {
    }
}
