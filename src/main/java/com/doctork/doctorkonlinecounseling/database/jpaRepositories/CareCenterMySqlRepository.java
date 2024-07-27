package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.CareCenterEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareCenterMySqlRepository extends JpaRepository<CareCenterEntity , Long> {

    List<CareCenterEntity> findAllByStatus (Status status);

}
