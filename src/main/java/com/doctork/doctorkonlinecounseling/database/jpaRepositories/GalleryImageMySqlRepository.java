package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.GalleryImageEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface GalleryImageMySqlRepository extends JpaRepository<GalleryImageEntity, Long> {

    Optional<List<GalleryImageEntity>> findAllByPhysicianAndAndStatus(PhysicianEntity physicianEntity , Status status);
}
