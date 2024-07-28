package com.doctork.doctorkonlinecounseling.boundary.in.price;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;

import java.util.List;

public interface PriceService {


    Services addServices(Services services);
    Price addPrice(Price price, String physicianId, Long servicesId);
    Price DeActivePrice(String physicianId,Long priceId);
    Price priceAcceptanceDecision (Long priceId , State state);
    List<Services> AllActiveServices ();
    List<Price> readPrices(String physicianId);

}
