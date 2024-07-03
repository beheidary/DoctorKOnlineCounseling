package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.PhysicianSocialMediaEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.PhysicianSocialMediaId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PhysicianSocialMediaMySqlRepository extends JpaRepository<PhysicianSocialMediaEntity, PhysicianSocialMediaId> {
}
