package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.AwardsAndHonorsEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AwardsAndHonorsMySqlRepository extends JpaRepository<AwardsAndHonorsEntity , Long> {

    Optional<AwardsAndHonorsEntity> findAwardsAndHonorsEntityByPhysicianAndId(PhysicianEntity physicianEntity , Long AwardsAndHonorsId);
}
