package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.user.PatientEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientMySqlRepository extends JpaRepository<PatientEntity, Long> {

    PatientEntity findPatientEntityByNationalCode(Long nationalCode);

    PatientEntity findPatientEntityByUser(UserEntity user);


}
