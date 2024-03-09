package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface DoctorMySqlRepository extends JpaRepository <DoctorEntity , Long> {

    DoctorEntity findDoctorEntityByPhysicianSystemCode(String PSCode);


}
