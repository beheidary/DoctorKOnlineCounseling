package com.doctork.doctorkonlinecounseling.UseCase.Price;

import com.doctork.doctorkonlinecounseling.boundary.exit.Price.PriceRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.price.PriceService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
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
    public Price addPrice(Price price, Long physicianId, Long servicesId) {
        if(physicianId == null || servicesId == null)
            throw new IdInputException();
        UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        return priceRepository.addPrice(price , physicianId, servicesId);
    }

    @Override
    public Price editPrice(Long priceId, Price price) {
        if(priceId == null)
            throw new IdInputException();
        return priceRepository.editPrice(priceId, price);
    }

    @Override
    public List<Price> readPrices(Long physicianId) {
        if(physicianId == null)
            throw new IdInputException();
        return priceRepository.readPrices(physicianId);
    }

    @Override
    public Long deletePrice(Long priceId) {
        if(priceId == null)
            throw new IdInputException();
        return priceRepository.deletePrice(priceId);
    }
}
