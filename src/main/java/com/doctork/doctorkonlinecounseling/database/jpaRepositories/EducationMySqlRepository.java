package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.EducationEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.ExperiencesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EducationMySqlRepository extends JpaRepository<EducationEntity , Long> {

    Optional<EducationEntity> findEducationEntityByPhysicianAndId(PhysicianEntity physicianEntity , Long EducationId);


}
