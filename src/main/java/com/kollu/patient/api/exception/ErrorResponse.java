package com.kollu.patient.api.exception;

import java.io.Serializable;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private String message;
	
    private List<String> details;
    
}
