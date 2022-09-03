package fr.calendly.entities;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data @AllArgsConstructor @NoArgsConstructor(access = AccessLevel.PROTECTED) // bonne pratique car constructeur utilisable uniquement par JPA
@ToString
@Entity
@Table(name="calendar_table")
public class Calendar implements Serializable {

	private static final long serialVersionUID = 1182711840073759465L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@OneToOne
	@JoinColumn(name = "employee_number")
	private Employee employee;
	
	@OneToMany(targetEntity = Event.class, mappedBy="calendar", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<Event> setEvents = new HashSet<>();
	
	@OneToMany(targetEntity = SharedCalendar.class, mappedBy="sharedCalendar", fetch = FetchType.EAGER, cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
	private Set<SharedCalendar> setSharedCalendars = new HashSet<>();

	
}
