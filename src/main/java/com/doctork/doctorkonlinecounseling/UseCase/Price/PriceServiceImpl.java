package com.doctork.doctorkonlinecounseling.UseCase.Price;

import com.doctork.doctorkonlinecounseling.boundary.exit.Price.PriceRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.price.PriceService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PriceServiceImpl implements PriceService {

    private final PriceRepository priceRepository;

    public PriceServiceImpl(PriceRepository priceRepository) {
        this.priceRepository = priceRepository;
    }



    @Override
    public Services addServices(Services services) {

        return priceRepository.addServices(services);
    }

    @Override
    public Price addPrice(Price price, String physicianId, Long servicesId) {
        if(physicianId == null || servicesId == null)
            throw new IdInputException();
        return priceRepository.addPrice(price , physicianId, servicesId);
    }

    @Override
    public Price DeActivePrice(String physicianId, Long priceId) {
        if(priceId == null || physicianId == null)
            throw new IdInputException();
        return priceRepository.DeActivePrice(physicianId , priceId);
    }

    @Override
    public Price priceAcceptanceDecision(Long priceId, State state) {
        if(priceId == null)
            throw new IdInputException();
        return priceRepository.priceAcceptanceDecision(priceId,state);
    }

    @Override
    public List<Services> AllActiveServices() {
        return priceRepository.AllActiveServices();
    }

    @Override
    public List<Price> readPrices(String physicianId) {
        if(physicianId == null)
            throw new IdInputException();
        return priceRepository.readPrices(physicianId);
    }
}
