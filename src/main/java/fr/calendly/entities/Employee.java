package fr.calendly.entities;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Data @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED) // bonne pratique car constructeur utilisable uniquement par JPA
@ToString
@Entity
@Table(name="employee_table")
public class Employee implements Serializable {

	private static final long serialVersionUID = -5661077846121582518L;

	@Basic
	@Column(name="first_name")
	private String firstName;
	
	@Basic
	@Column(name="last_name")
	private String lastName;
	
	@Basic
	private String email;
	
	@Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
	@Column(name="employee_number", columnDefinition = "VARCHAR(36)")
	@Type(type = "uuid-char")
	private UUID employeeNumber;
	
	@JsonIgnore
	@OneToOne(targetEntity = Calendar.class, mappedBy="employee", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Calendar calendar;
	
	@JsonIgnore
	@OneToMany(targetEntity = SharedCalendar.class, mappedBy="sharedEmployee", fetch = FetchType.EAGER)
	private Set<SharedCalendar> setSharedCalendars = new HashSet<>();
	
	@Basic
	@Column(name="address_line1")
	private String addressLine1;
	
	@Basic
	@Column(name="address_line2")
	private String addressLine2;
	
	@Basic
	@Column(name="postal_Code")
	private String postalCode;
	
	@Basic
	private String city;
	
	@Column(name="birth_date")
//	@JsonDeserialize(using = LocalDateDeserializer.class)  
//	@JsonSerialize(using = LocalDateSerializer.class) 
	private LocalDate birthDate;
	
	@Basic
	@Column(name="phone_number")
	private String phoneNumber;
	
	@Basic
	@Column(name="employee_function")
	private String employeeFunction;
	
	@Basic
	@Column(name="image_file_name")
	private String imageFileName;
	
	@JsonIgnore
	@Basic
	@Column(name="employee_password")
	private String employeePassword;
	
	@Basic
	@Column(name="can_share")
	private boolean canShare;
	
	@Basic
	@Column(name="can_modify")
	private boolean canModify;
	
	@Basic
	@Column(name="first_login")
	private boolean firstLogin;
	
	@Basic
	private boolean activated;

}
