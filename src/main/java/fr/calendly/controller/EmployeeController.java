package fr.calendly.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fr.calendly.entities.Employee;
import fr.calendly.models.Login;
import fr.calendly.service.EmployeeService;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
	@Autowired
	private EmployeeService es;
	
	
	@RequestMapping(value = "/all", method = RequestMethod.GET, 
					produces = MediaType.APPLICATION_JSON_VALUE)
	public HttpEntity<Iterable<Employee>> getAll() {
		return new ResponseEntity<>(es.getAll(), HttpStatus.OK);
	}
	
	public HttpEntity<String> login(Login login) {
		if (es.isCredentialsOk(login)) {
			// token
			return new ResponseEntity<>(null, HttpStatus.OK);
		}
		else {
			
		}
		
	}
	
	
	
}

