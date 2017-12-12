package eu.senla.andyavd.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface CsvEntity {
    String filename();
    String valuesSeparator() default ",";
    String entityId() default "id";
}
