package lesson6.hotel.src.eu.senla.andyavd.entities;

import java.io.Serializable;

public abstract class AEntity implements Serializable{

	private static final long serialVersionUID = -777759761815376217L;

	protected abstract int getId();

	public abstract void setId(int id);

}
