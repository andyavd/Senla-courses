package eu.senla.andyavd;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import eu.senla.andyavd.enums.PropertyType;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {
	
	PropertyType propertyType();
	
	int columnNumber();

	String keyField() default "";
}