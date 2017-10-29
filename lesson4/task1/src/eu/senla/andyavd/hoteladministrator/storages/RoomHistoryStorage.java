package eu.senla.andyavd.hoteladministrator.storages;

import eu.senla.andyavd.hoteladministrator.entities.RoomHistory;

public class RoomHistoryStorage {
	
	private RoomHistory[] histories = new RoomHistory[10];

	public void addHistory(RoomHistory history) {
		for (int i = 0; i < histories.length; i++) {
			
			 if(i == histories.length - 1) {
			 RoomHistory[] stretchedArray = new RoomHistory[histories.length * 2];
			 System.arraycopy(histories, 0, stretchedArray, 0, histories.length - 1);
			 histories = stretchedArray;
			 }
			 
			if (histories[i] == null) {
				histories[i] = history;
				histories[i].setId(i + 1);
//				System.out.println(histories[i].toString());
				break;
			}
		}
	}
	
	public RoomHistory[] getHistory() {
		return histories;
	}

}
