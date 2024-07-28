package com.doctork.doctorkonlinecounseling.api.adapters.Price;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;

import java.util.List;
import java.util.UUID;

public interface PriceAdapter {


    ServicesOutputDto addServices(ServicesInputDto servicesInputDto);
    PriceOutputDto addPrice(PriceInputDto priceInputDto, UUID userId, Long servicesId);
    PriceOutputDto DeActivePrice(UUID userId, Long priceId);

    PriceOutputDto priceAcceptanceDecision (Long priceId , State state);

    List<ServicesOutputDto> AllActiveServices ();
    List<PriceOutputDto> readPrices(UUID userId);

}
