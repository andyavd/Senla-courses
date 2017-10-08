import java.util.*;
import java.io.*;

public class IdCard{

	private int number;
	private String validTill;

	public void setNumber(int newNumber){
		number = newNumber;
	}
	public int getNumber(){
		return number;
	}
	public void setValidTill(String newValidTill){
		validTill = newValidTill;
	}
	public String getValidTill(){
		return validTill;
	}

	public IdCard(int nm, String vt){
		number = nm;
		validTill = vt;
	}

}