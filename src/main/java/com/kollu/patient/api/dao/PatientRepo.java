package com.kollu.patient.api.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kollu.patient.api.entity.PatientEntity;

@Repository
public interface PatientRepo extends JpaRepository<PatientEntity, Long>{

}
