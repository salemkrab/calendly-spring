package fr.calendly.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;

import fr.calendly.entities.Employee;
@Component
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, UUID> {
	// EmployeeRepository etend PagingAndSortingRepository qui etend lui meme CrudRepo (plus de fonctions 
	// dont on en aura besoin peut etre)
  	

	Optional<Employee> findByEmail(String email);

	
}

