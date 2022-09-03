package fr.calendly.entities;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Basic;
import javax.persistence.Column;
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
@Table(name="event_table")
public class Event implements Serializable {

	private static final long serialVersionUID = -7102846837065132540L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "calendar_id")
	private Calendar calendar;
	
	@Basic
	private String title;
	
	@Column(name="begin_date")
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)  
//	@JsonSerialize(using = LocalDateTimeSerializer.class) 
	private LocalDateTime beginDate;
	
	@Column(name="end_date")
//	@JsonDeserialize(using = LocalDateTimeDeserializer.class)  
//	@JsonSerialize(using = LocalDateTimeSerializer.class) 
	private LocalDateTime endDate;
}
