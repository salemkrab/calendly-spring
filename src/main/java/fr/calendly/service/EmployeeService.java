package fr.calendly.service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

// apache common codec embarqué dans Spring mais l'embarqué ne contient pas la méthode sha1Hex
// -> warning dans le pom
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.calendly.entities.Employee;
import fr.calendly.exception.ResourceNotFoundException;
import fr.calendly.models.EmployeeUpdateByAdmin;
import fr.calendly.models.EmployeeUpdateByHimself;
import fr.calendly.models.Login;
import fr.calendly.repository.EmployeeRepository;
import fr.calendly.utils.Utils;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository er;

	public Iterable<Employee> getAll() {
		return er.findAll();
	};

	public Optional<Employee> getById(UUID id) {
		if (er.existsById(id)) {
			return er.findById(id);
		} else {
			return Optional.empty();
		}
	};

	public Optional<Employee> getbyEmail(String email) {
		Optional<Employee> employeeOptional = er.findByEmail(email);
		if (employeeOptional.isPresent()) {
			return employeeOptional;
		} else {
			return Optional.empty();
		}
	};

	public Employee save(Employee employee) {
		return er.save(employee);
	};

	public void updatePassword(UUID id, Map<String, String> passwordMap) throws ResourceNotFoundException {
		// only if employee is activated
		Optional<Employee> employee = this.getById(id);
		if (employee.isPresent()) {
			String sha1SaltedPass = Utils.generateSha1SaltedPassword(passwordMap.get("password"));
			employee.get().setEmployeePassword(sha1SaltedPass);
			er.save(employee.get());
		} else {
			throw new ResourceNotFoundException(id);
		}
	}

	public Optional<Employee> updateInfos(UUID id, EmployeeUpdateByHimself updateFields) throws ResourceNotFoundException {
		// only if employee is activated
		Optional<Employee> employee = this.getById(id);
		if (employee.isPresent()) {
			employee.get().setAddressLine1(updateFields.getAddressLine1());
			employee.get().setAddressLine2(updateFields.getAddressLine2());
			employee.get().setPostalCode(updateFields.getPostalCode());
			employee.get().setCity(updateFields.getCity());
			employee.get().setPhoneNumber(updateFields.getPhoneNumber());
			er.save(employee.get());
			return employee;
		} else {
			throw new ResourceNotFoundException(id);
		}

	}

	public void updateInfosByAdmin(UUID id, EmployeeUpdateByAdmin updateFields) throws ResourceNotFoundException {
		Optional<Employee> employee = this.getById(id);
		if (employee.isPresent()) {
			employee.get().setEmail(updateFields.getEmail());
			employee.get().setEmployeeFunction(updateFields.getEmployeeFunction());
			employee.get().setCanShare(updateFields.isCanShare());
			employee.get().setCanModify(updateFields.isCanModify());
			er.save(employee.get());
			
		} else {
			throw new ResourceNotFoundException(id);
		}
	}

	public Optional<Employee> updateFirstLogin(UUID id, boolean isFirstLogin) throws ResourceNotFoundException {
		// only if employee is activated
		Optional<Employee> employee = this.getById(id);
		if (employee.isPresent()) {
			employee.get().setFirstLogin(isFirstLogin);
			er.save(employee.get());

			return employee;
		}
		else {
			throw new ResourceNotFoundException(id);
		}

	}

	public void updateAccountActivation(UUID id, Map<String, Boolean> activationMap) throws ResourceNotFoundException {
		Optional<Employee> employee = this.getById(id);
		if (employee.isPresent()) {
			employee.get().setActivated(activationMap.get("activation"));
			er.save(employee.get());

		}
		else {
			throw new ResourceNotFoundException(id);
		}
	}

	public void delete(UUID id) throws ResourceNotFoundException {
		Optional<Employee> employee = this.getById(id);
		if (employee.isPresent()) {
			er.deleteById(id);
		}
		else {
			throw new ResourceNotFoundException(id);
		}
	}
	
	public void updateImage(UUID id) {
		Optional<Employee> employee = this.getById(id);
		if (employee.isPresent()) {
		}
		else {
			// todo throw notfound
		}
	}
	
	public boolean isCredentialsOk(Login login) {
		Optional<Employee> employeeOptional = er.findByEmail(login.getEmail());
		if (employeeOptional.isPresent()) {
			 String saltedPassword = Utils.generateSha1SaltedPassword(login.getEmail());
			 return (employeeOptional.get().getEmployeePassword().equals(saltedPassword));
		}
		return false;
		
	}
}
