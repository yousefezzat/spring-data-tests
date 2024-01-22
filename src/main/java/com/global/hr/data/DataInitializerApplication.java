package com.global.hr.data;

import java.util.ArrayList;

import org.hibernate.mapping.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.global.hr.data.model.entity.Employee;
import com.global.hr.data.service.EmployeeService;

@SpringBootApplication
public class DataInitializerApplication {

	public static void main(String[] args) {
		SpringApplication.run(DataInitializerApplication.class, args);
	}

//	// to make it as a console not web service
//	@Bean
//	public CommandLineRunner commandLineRunner(@Autowired EmployeeService employeeService) {
//		return args -> {
//			// Insert example
//			Employee newEmployee = new Employee(30L, "John", "Doe", 12000.0D);
//			Employee emp = employeeService.insert2(newEmployee);
//			System.out.println("Insert Result: " + emp.toString());
//
//			// GetById example
//			String name = "Ahmed"; // Assuming the ID you used for insertion
//
//			// to ensure that the retrieved results will be added as objects to the Employee
//			// list
//			// who is responsible for mapping? ORM maybe
//
//			java.util.List<Employee> retrievedEmployee = employeeService.filter(name);
//
//			if (retrievedEmployee != null) {
//				System.out.println("Retrieved Employee: " + "\n " + retrievedEmployee.get(0).getFirstName() + "\n "
//						+ retrievedEmployee.get(0).getLastName() + "\n " + retrievedEmployee.get(0).getId() + " \n "
//						+ retrievedEmployee.get(0).getSalary());
//				
//			} else {
//				System.out.println("Employee not found.");
//			}
//		};
//	}

}
