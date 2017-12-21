import java.util.*;
import java.io.*;

public class IdCard{

	private int number;
	private String validTill;

	public void setNumber(int number){
		this.number = number;
	}
	public int getNumber(){
		return number;
	}
	public void setValidTill(String validTill){
		this.validTill = validTill;
	}
	public String getValidTill(){
		return validTill;
	}

	public IdCard(int nm, String vt){
		number = nm;
		validTill = vt;
	}

}
