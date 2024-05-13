package com.doctork.doctorkonlinecounseling.api.adapters.Price;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.PriceMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.price.PriceService;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PriceAdapterImpl implements PriceAdapter {

    private final PriceMapper priceMapper;

    private final PriceService priceService;

    public PriceAdapterImpl(PriceMapper priceMapper, PriceService priceService) {
        this.priceMapper = priceMapper;
        this.priceService = priceService;
    }

    @Override
    public ServicesOutputDto addServices(ServicesInputDto servicesInputDto) {

        Services services = priceMapper.servicesDtoToModel(servicesInputDto);

        return  priceMapper.servicesModelToDto(priceService.addServices(services));

    }

    @Override
    public PriceOutputDto addPrice(PriceInputDto priceInputDto, Long physicianId, Long servicesId) {
        Price price =  priceMapper.priceDtoToModel(priceInputDto);
        return priceMapper.priceModelToDto(priceService.addPrice(price , physicianId, servicesId));
    }

    @Override
    public PriceOutputDto editPrice(Long priceId, PriceInputDto priceInputDto) {
        Price price =  priceMapper.priceDtoToModel(priceInputDto);
        return priceMapper.priceModelToDto(priceService.editPrice(priceId, price));
    }

    @Override
    public List<PriceOutputDto> readPrices(Long physicianId) {
        List<PriceOutputDto> priceOutputDtos = new ArrayList<>();
        for (Price price : priceService.readPrices(physicianId)){
            priceOutputDtos.add(priceMapper.priceModelToDto(price));
        }
        return priceOutputDtos;

    }

    @Override
    public Long deletePrice(Long priceId) {
        return priceService.deletePrice(priceId);
    }

}
