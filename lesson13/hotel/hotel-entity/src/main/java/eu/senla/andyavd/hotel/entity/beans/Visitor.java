package eu.senla.andyavd.hotel.entity.beans;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import eu.senla.andyavd.hotel.annotations.annotations.CsvEntity;
import eu.senla.andyavd.hotel.annotations.annotations.CsvProperty;
import eu.senla.andyavd.hotel.annotations.enums.PropertyType;

@Entity
@Table(name = "visitor")
@CsvEntity(filename = "data/visitors.csv")
public class Visitor extends AEntity implements Serializable {

	private static final long serialVersionUID = -5868446232504296194L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 1)
	private int id = 0;
	
	@Column(name = "visitor_name")
	@CsvProperty(propertyType = PropertyType.SimpleProperty, columnNumber = 2)
	private String lastName;

	public Visitor() {
	}

	public Visitor(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public int getId() {
		return id;
	}

	@Override
	public void setId(int id) {
		this.id = id;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return String.valueOf(new StringBuilder().append(id).append(" ").append(lastName));
	}
}