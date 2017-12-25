package lesson8.server.annotations.src.eu.senla.andyavd.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import lesson8.server.hotel.src.eu.senla.andyavd.enums.PropertyType;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {
	
	PropertyType propertyType();
	
	int columnNumber();

	String keyField() default "";
}