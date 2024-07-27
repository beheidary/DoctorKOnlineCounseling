package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.ProvinceCitiesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProvinceCitiesMySqlRepository extends JpaRepository<ProvinceCitiesEntity, Long> {

    List<ProvinceCitiesEntity> findAllByParent (Integer parentId);

}
