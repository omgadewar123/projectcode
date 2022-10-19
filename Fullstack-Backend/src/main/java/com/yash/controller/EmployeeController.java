package com.yash.controller;

import java.nio.file.attribute.UserPrincipalNotFoundException;

import java.util.List;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.yash.exception.UserNotFoundException;
import com.yash.model.Employee;
import com.yash.repository.EmployeeRepository;

@RestController
@CrossOrigin("http://localhost:3000")
public class EmployeeController {
	// private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

	@Autowired
	private EmployeeRepository employeerpository;
	
	@PostMapping("/employee")
	Employee newEmployee(@RequestBody Employee newEmployee) {
		return employeerpository.save(newEmployee);
	}
	
	@GetMapping("/employee")
	List<Employee> getAllEmployee(){
		return employeerpository.findAll();
		
	}
	
	@GetMapping("/employee/{id}")
	Employee getEmployeeById(@PathVariable Long id) {
		return employeerpository.findById(id).orElseThrow(()-> new UserNotFoundException(id));
		
	}
	
	@PutMapping("/employee/{id}")
	Employee updateEmployee(@RequestBody Employee newEmployee , @PathVariable Long id) {
		return employeerpository.findById(id)
				.map(employee -> {
					employee.setName(newEmployee.getName());
					employee.setUsername(newEmployee.getUsername());
					employee.setEmail(newEmployee.getEmail());
					return employeerpository.save(employee);
				}).orElseThrow(()-> new UserNotFoundException(id));
		
	}
	@DeleteMapping("/employee/{id}")
	String deleteEmployee(@PathVariable Long id) {
		if(!employeerpository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		employeerpository.deleteById(id);
		return "Employee with id "+id +"has been deleted success.";
	}

}
