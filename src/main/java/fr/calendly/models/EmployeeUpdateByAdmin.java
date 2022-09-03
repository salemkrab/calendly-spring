package fr.calendly.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class EmployeeUpdateByAdmin {
	private String email;
	private String employeeFunction;
	private boolean canShare;
	private boolean canModify;
}
