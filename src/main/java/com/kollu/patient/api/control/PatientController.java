package com.kollu.patient.api.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kollu.patient.api.dto.Patient;
import com.kollu.patient.api.entity.PatientEntity;
import com.kollu.patient.api.service.PatientDao;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/p")
@Slf4j
public class PatientController {
	
	@Autowired
	private PatientDao patientDao;

	
	@GetMapping("/pfetchall")
	public ResponseEntity<Object> fetchAllPatientDetails(){
		try {
			
			List<Patient> patients = null;
			List<PatientEntity>  patientEntities =patientDao.findAll();
			
			if(patientEntities.isEmpty()) {
				log.info("PatientEntity is Empty");
				return new ResponseEntity<>("Patient Details Not Available", HttpStatus.NOT_FOUND);
			} else {
				patients = patientEntities.stream()
						.map(p -> new Patient(p.getPId(), p.getPFirstName(), p.getPLastName(), p.getPAddress()))
						.collect(Collectors.toList());
				log.debug("Patients size::", patients.size());
			}
		
			return new ResponseEntity<>(patients, HttpStatus.OK);
		}catch (Exception e) {
			log.error("Error while fetching Patient Details::", e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PostMapping("/psave")
	public ResponseEntity<Object> savePatientDetails(@Valid @RequestBody Patient patient){
		try {
		
			List<PatientEntity> entities = new ArrayList<>();
			PatientEntity entity = new PatientEntity();
			
			entity.setPFirstName(patient.getPatientFirstName());
			entity.setPLastName(patient.getPatientLastName());
			entity.setPAddress(patient.getPatientAddress());
			
			entities.add(entity);
			entities = patientDao.saveAll(entities);
			
			return new ResponseEntity<>("Patient Details Saved Successfully!", HttpStatus.CREATED);
		}catch (Exception e) {
			log.error("Error while saving Patient details", e.getLocalizedMessage());
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@PutMapping("/pupdate/{pid}")
	public ResponseEntity<Object> updatePatientDetails(@PathVariable("pid") long pid, @RequestBody Patient patient){
		log.info("PID::"+pid);
		try {
			Optional<PatientEntity> pEntity = patientDao.findById(pid);
			log.debug("PID::" +pEntity);
			
			PatientEntity entity = null;
			List<PatientEntity> entities = new ArrayList<>();
			
			if(pEntity.isPresent()) {
				 entity= pEntity.get();
			} else {
				return new ResponseEntity<Object>("Patient Id Invalid", HttpStatus.NOT_FOUND);
			}
			
			entity.setPFirstName(patient.getPatientFirstName());
			entity.setPLastName(patient.getPatientLastName());
			entity.setPAddress(patient.getPatientAddress());
			
			entities.add(entity);
			entities = patientDao.saveAll(entities);
			
			return new ResponseEntity<>("Updated Patient Details Successfully", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Error while Updating Patient Details ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	
	@DeleteMapping("/pdelete/{pid}")
	public ResponseEntity<Object> deletePatientDetails(@PathVariable("pid") long pid){
		try {
			
			Optional<PatientEntity> pEntity = patientDao.findById(pid);
			log.debug("PID::" +pEntity);
			
			if(pEntity.isPresent()) {
				patientDao.deleteById(pid);
			} else {
				return new ResponseEntity<Object>("Patient Id Invalid", HttpStatus.NOT_FOUND);
			}
		
			return new ResponseEntity<>("Patient Record Deleted Successfully ", HttpStatus.OK);
		}catch (Exception e) {
			return new ResponseEntity<>("Error while Deleting Patient Records ",HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
