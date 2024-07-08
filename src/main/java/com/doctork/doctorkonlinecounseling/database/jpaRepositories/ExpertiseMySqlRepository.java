package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Expertise.ExpertiseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertiseMySqlRepository extends JpaRepository<ExpertiseEntity,Long> {

    ExpertiseEntity findExpertiseEntitiesByLatinName(String latinName);

}
