package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.PhysicianBankInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PhysicianBankInfoMySqlRepository extends JpaRepository<PhysicianBankInfoEntity, Long> {
    Optional<PhysicianBankInfoEntity> findByPhysician (PhysicianEntity physicianEntity);

}
