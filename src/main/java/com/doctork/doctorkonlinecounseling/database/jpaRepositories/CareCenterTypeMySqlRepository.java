package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.CareCenterTypeEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CareCenterTypeMySqlRepository extends JpaRepository<CareCenterTypeEntity , Long> {

    List<CareCenterTypeEntity> findAllByStatus (Status status);

}
