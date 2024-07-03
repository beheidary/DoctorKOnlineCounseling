package com.doctork.doctorkonlinecounseling.boundary.exit.Price;

import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface PriceRepository {


    Services addServices(Services services);

    Price addPrice(Price price, String physicianId, Long ServicesId);
    Price editPrice(Long priceId , Price price);
    List<Price> readPrices(String physicianId);

    Price findPriceById (Long priceId);

    Long deletePrice (Long priceId);

}
