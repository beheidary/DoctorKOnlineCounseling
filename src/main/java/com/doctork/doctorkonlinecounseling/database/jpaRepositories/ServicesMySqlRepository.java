package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Price.ServicesEntity;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ServicesMySqlRepository extends JpaRepository <ServicesEntity, Long> {

    ServicesEntity findServicesEntityById(Long id);

}
