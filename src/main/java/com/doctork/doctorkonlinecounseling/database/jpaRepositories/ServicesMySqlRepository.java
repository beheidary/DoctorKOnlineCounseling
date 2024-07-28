package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Price.ServicesEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface ServicesMySqlRepository extends JpaRepository <ServicesEntity, Long> {

    ServicesEntity findServicesEntityById(Long id);
    List<ServicesEntity> findAllByStatus(Status status);

}
