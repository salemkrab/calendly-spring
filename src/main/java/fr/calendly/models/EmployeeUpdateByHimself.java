package fr.calendly.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class EmployeeUpdateByHimself {

	private String addressLine1;
	

	private String addressLine2;
	

	private String postalCode;
	
	private String city;
	
	private String phoneNumber;
}
