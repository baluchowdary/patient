package com.kollu.patient.api.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private long patientId;
	private String patientFirstName;
	private String patientLastName;
	private String patientAddress;

}
