package com.kollu.patient.api.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "PATIENT_DATA")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PatientEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patientId_generator")
	@SequenceGenerator(name="patientId_generator", sequenceName = "patient_seq", allocationSize=1)
	private Long pId;
	
	@Column(name = "P_FIRST_NAME")
	private String pFirstName;
	
	@Column(name = "P_LAST_NAME")
	private String pLastName;
	
	@Column(name = "P_ADDRESS")
	private String pAddress;
}
