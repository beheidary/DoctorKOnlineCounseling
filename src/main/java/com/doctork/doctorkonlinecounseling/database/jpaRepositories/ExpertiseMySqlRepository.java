package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpertiseMySqlRepository extends JpaRepository<ExpertiseEntity,Long> {

    ExpertiseEntity findExpertiseEntitiesByLatinName(ExpertiseLatinNames latinName);

}
