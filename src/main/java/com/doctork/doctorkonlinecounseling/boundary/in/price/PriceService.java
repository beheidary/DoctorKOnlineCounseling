package com.doctork.doctorkonlinecounseling.boundary.in.price;

import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;

import java.util.List;

public interface PriceService {


    Services addServices(Services services);
    Price addPrice(Price price, Long physicianId, Long servicesId);
    Price editPrice(Long priceId , Price price);
    List<Price> readPrices(Long physicianId);
    Long deletePrice (Long priceId);

}
