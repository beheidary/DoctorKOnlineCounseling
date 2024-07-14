package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.EducationEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipMySqlRepository  extends JpaRepository<MembershipEntity , Long> {

    Optional<MembershipEntity> findMembershipEntityByPhysicianAndId(PhysicianEntity physicianEntity , Long membershipId);




}
