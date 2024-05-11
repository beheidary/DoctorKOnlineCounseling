package com.doctork.doctorkonlinecounseling.database.jpaRepositories;

import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceMySqlRepository extends JpaRepository<PriceEntity , Long> {

    PriceEntity findPriceEntityById(Long id);

}
