package com.doctork.doctorkonlinecounseling.boundary.exit.Price;

import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PriceRepository {


    Services addServices(Services services);

    Price addPrice(Price price, Long physicianId, Long ServicesId);
    Price editPrice(Long priceId , Price price);
    List<Price> readPrices(Long physicianId);

    Price findPriceById (Long priceId);

    Long deletePrice (Long priceId);

}
