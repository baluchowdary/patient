package com.kollu.patient.api.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.kollu.patient.api.dao.PatientRepo;
import com.kollu.patient.api.entity.PatientEntity;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@CacheConfig(cacheNames = "patientEntity")
public class PatientService {
	
	@Autowired
	private PatientRepo patientRepo;
	
	
	@Cacheable(value = "patientEntity")
	public List<PatientEntity> findAllPatientDetails() { 
		log.info("PatientService.findAllPatientDetails()");
		return patientRepo.findAll();
	}
	@Cacheable(value = "patientEntity")
	public List<PatientEntity> saveAllPatientDetails(List<PatientEntity> entitiesList) {
		log.info("PatientService.savePatientDetails()");
		log.debug("PatientService.entitiesList ::" +entitiesList.size()); 
		return patientRepo.saveAll(entitiesList);
	}
	
	@CachePut(value = "patientEntity")
	public PatientEntity updatePatientDetails(PatientEntity entity) {
		log.info("PatientService.updatePatientDetails()");
		//log.debug("updatePatientDetails.PId ::" +entity.getPId());  
		return patientRepo.save(entity);  
	}
	
	@Cacheable(value = "patientEntity")
	public Optional<PatientEntity> findPatientById(Long pid) { 
		log.info("PatientService.findPatientById()");
		log.debug("PatientService.findById ::" +pid);  
		return patientRepo.findById(pid); 
	}

	@CacheEvict(value = "patientEntity")
	public void deletePatientById(Long pid) { 
		log.info("PatientService.deletePatientById()");
		log.debug("PatientService.deleteById ::" +pid); 
		patientRepo.deleteById(pid);
	}
}
