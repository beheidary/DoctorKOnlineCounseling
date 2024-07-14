package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.ExperiencesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExperiencesMySqlRepository extends JpaRepository<ExperiencesEntity , Long> {

    Optional<ExperiencesEntity> findExperiencesEntityByPhysicianAndId(PhysicianEntity physicianEntity , Long ExperienceId);
}
