package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Price.PriceEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PriceMySqlRepository extends JpaRepository<PriceEntity , Long> {

    PriceEntity findPriceEntityById(Long id);

    Optional<PriceEntity> findByServiceIdAndTimeAndPriceStatus(Long serviceId, Long time, Status activeStatus);

}
