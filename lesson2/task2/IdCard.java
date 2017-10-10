import java.util.*;
import java.io.*;

public class IdCard{

	private int number;
	private String validTill;

	public void setNumber(int number){
		this.number = number;
	}
	public int getNumber(){
		return this.number;
	}
	public void setValidTill(String validTill){
		this.validTill = validTill;
	}
	public String getValidTill(){
		return this.validTill;
	}

	public IdCard(int nm, String vt){
		number = nm;
		validTill = vt;
	}

}
