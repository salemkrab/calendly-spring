package fr.calendly.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="shared_calendar_table")
public class SharedCalendar implements Serializable {

	private static final long serialVersionUID = -8000112242225954716L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "calendar_id")
	private Calendar sharedCalendar;
		
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "shared_employee_number")
	private Employee sharedEmployee;

	
}
