package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.EducationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EducationMySqlRepository extends JpaRepository<EducationEntity , Long> {
}
