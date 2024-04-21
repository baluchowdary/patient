package com.kollu.patient.api.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	
	private Long patientId; 
	
	@Size(min = 2, max = 20, message = "First Name should between 2 to 20 letters only")
	private String patientFirstName;
	
	@Size(min = 2, max = 20, message = "Last Name should between 2 to 20 letters only")
	private String patientLastName;
	
	@NotBlank(message = "Please Select Gender Type ")
	private String patientGender;
	
	@NotNull(message = "Age should not be empty")
	private Integer patientAge;
	
	@Size(min = 2, max = 20, message = "Address minimum 2 letters and should not exceed more than 20 letters")
	private String patientAddress;
	
	
	

}
