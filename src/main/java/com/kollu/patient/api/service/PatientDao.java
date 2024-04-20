package com.kollu.patient.api.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kollu.patient.api.entity.PatientEntity;

@Repository
public interface PatientDao extends JpaRepository<PatientEntity, Long>{

}
