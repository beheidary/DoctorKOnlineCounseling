package com.doctork.doctorkonlinecounseling.api.adapters.Price;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.PriceMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Physician.PhysicianService;
import com.doctork.doctorkonlinecounseling.boundary.in.price.PriceService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class PriceAdapterImpl implements PriceAdapter {

    private final PriceMapper priceMapper;

    private final PhysicianService physicianService;

    private final PriceService priceService;

    public PriceAdapterImpl(PriceMapper priceMapper,PhysicianService physicianService, PriceService priceService) {
        this.priceMapper = priceMapper;
        this.physicianService = physicianService;
        this.priceService = priceService;
    }

    @Override
    public ServicesOutputDto addServices(ServicesInputDto servicesInputDto) {

        Services services = priceMapper.servicesDtoToModel(servicesInputDto);
        services.setStatus(Status.Active);
        return  priceMapper.servicesModelToDto(priceService.addServices(services));

    }

    @Override
    public PriceOutputDto addPrice(PriceInputDto priceInputDto, UUID userId, Long servicesId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        Price price =  priceMapper.priceDtoToModel(priceInputDto);
        price.setPriceStatus(Status.Active);
        price.setState(State.Waiting);
        return priceMapper.priceModelToDto(priceService.addPrice(price , physician.getNationalCode(), servicesId));
    }

    @Override
    public PriceOutputDto DeActivePrice(UUID userId, Long priceId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return priceMapper.priceModelToDto(priceService.DeActivePrice(physician.getNationalCode() , priceId));
    }

    @Override
    public PriceOutputDto priceAcceptanceDecision(Long priceId, State state) {
        return priceMapper.priceModelToDto(priceService.priceAcceptanceDecision(priceId,state));
    }

    @Override
    public List<ServicesOutputDto> AllActiveServices() {
        return priceMapper.servicesModelToDto(priceService.AllActiveServices());
    }


    @Override
    public List<PriceOutputDto> readPrices(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return priceMapper.priceModelToDto(priceService.readPrices(physician.getNationalCode()));

    }
}
