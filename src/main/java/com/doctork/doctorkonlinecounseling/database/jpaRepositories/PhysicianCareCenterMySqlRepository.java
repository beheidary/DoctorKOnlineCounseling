package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.PhysicianCareCenterEntity;
import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.PhysicianCareCenterId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhysicianCareCenterMySqlRepository extends JpaRepository<PhysicianCareCenterEntity, PhysicianCareCenterId> {

    List<PhysicianCareCenterEntity> findAllByPhysicianId (String physicianId);
    List<PhysicianCareCenterEntity> findAllByCareCenterId (Long careCenterId);


}
