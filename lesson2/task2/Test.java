import java.util.*;
import java.io.*;

public class Test{
	public static void main(String[] args){
		
		Department javaDevelopers = new Department("Java Developers");
		System.out.println("Department " + javaDevelopers.getName() + " was created");

		Employee developer1 = new Employee("John", "Doe", "Junior Developer");
		System.out.println("Developer 1 was created");
		Employee developer2 = new Employee("Jane", "Doe", "Middle Developer");
		System.out.println("Developer 2 was created");
		Employee developer3 = new Employee("James", "Doe", "Senior Developer");
		System.out.println("Developer 3 was created");

		IdCard card1 = new IdCard(111, "29 of December, 2017");
		System.out.println("IdCard 1 was created");
		IdCard card2 = new IdCard(222, "30 of December, 2017");
		System.out.println("IdCard 2 was created");
		IdCard card3 = new IdCard(333, "31 of December, 2017");
		System.out.println("IdCard 3 was created");
		
		developer1.setIdCard(card1);
		System.out.println("IdCard # " + card1.getNumber() + " was given to an Employee " + developer1.getFirstName() + " " + developer1.getLastName());
		developer2.setIdCard(card2);
		System.out.println("IdCard # " + card2.getNumber() + " was given to an Employee " + developer2.getFirstName() + " " + developer2.getLastName());
		developer3.setIdCard(card3);
		System.out.println("IdCard # " + card3.getNumber() + " was given to an Employee " + developer3.getFirstName() + " " + developer3.getLastName());

		javaDevelopers.addEmployee(developer1);
		System.out.println(developer1.getFirstName() + " " + developer1.getLastName() + " starts working in the department " + developer1.getDepartment().getName());
		javaDevelopers.addEmployee(developer2);
		System.out.println(developer2.getFirstName() + " " + developer2.getLastName() + " starts working in the department " + developer2.getDepartment().getName());
		javaDevelopers.addEmployee(developer3);
		System.out.println(developer3.getFirstName() + " " + developer3.getLastName() + " starts working in the department " + developer3.getDepartment().getName());


		System.out.println(developer1.getFirstName() + " " + developer1.getLastName() + " works as " + developer1.getPosition() + ", has IdCard # " + card1.getNumber() + " valid till " + card1.getValidTill() + ". Works in " + developer1.getDepartment().getName() + " department");
		System.out.println(developer2.getFirstName() + " " + developer2.getLastName() + " works as " + developer2.getPosition() + ", has IdCard # " + card2.getNumber() + " valid till " + card2.getValidTill() + ". Works in " + developer1.getDepartment().getName() + " department");
		System.out.println(developer3.getFirstName() + " " + developer3.getLastName() + " works as " + developer3.getPosition() + ", has IdCard # " + card3.getNumber() + " valid till " + card3.getValidTill() + ". Works in " + developer1.getDepartment().getName() + " department");
		
		System.out.println("Total number of employees in the department " + javaDevelopers.getName() + " is " + javaDevelopers.getPersonCount());
	}
}