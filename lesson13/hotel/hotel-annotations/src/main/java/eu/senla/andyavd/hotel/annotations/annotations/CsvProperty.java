package eu.senla.andyavd.hotel.annotations.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import eu.senla.andyavd.hotel.annotations.enums.PropertyType;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvProperty {

	PropertyType propertyType();

	int columnNumber();

	String keyField() default "";
}