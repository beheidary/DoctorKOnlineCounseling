package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.SicknessEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface SicknessMySqlRepository extends JpaRepository<SicknessEntity , Long> {

//    List<SicknessEntity>

}
