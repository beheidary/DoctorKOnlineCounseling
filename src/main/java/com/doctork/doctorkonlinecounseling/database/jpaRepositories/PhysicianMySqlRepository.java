package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;

import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PhysicianMySqlRepository extends JpaRepository <PhysicianEntity, String> {

    PhysicianEntity findPhysicianEntityByUser(UserEntity user);

    PhysicianEntity findPhysicianEntityByNationalCode(String nationalCode);

    PhysicianEntity findPhysicianEntityByPhysicianSystemCode(String physicianSystemCode);
    PhysicianEntity findPhysicianEntityByPhysicianSystemCodeOrNationalCode(String physicianSystemCode,String nationalCode);


}
