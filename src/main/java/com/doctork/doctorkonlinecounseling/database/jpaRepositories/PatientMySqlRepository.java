package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Patient.PatientEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientMySqlRepository extends JpaRepository<PatientEntity, String> {

    PatientEntity findPatientEntityByNationalCode(String nationalCode);

    PatientEntity findPatientEntityByUser(UserEntity user);


}
