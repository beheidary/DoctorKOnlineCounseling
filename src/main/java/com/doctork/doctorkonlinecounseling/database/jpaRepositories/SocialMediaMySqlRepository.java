package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.SocialMediaEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SocialMediaMySqlRepository extends JpaRepository<SocialMediaEntity, Long> {
}
