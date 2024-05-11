package com.doctork.doctorkonlinecounseling.api.adapters.miscellaneous;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.PriceInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ArticleOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.FAQOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.PriceOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.MiscellaneousMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.miscellaneous.MiscellaneousService;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Price;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Services;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MiscellaneousAdapterImpl implements MiscellaneousAdapter {

    private final MiscellaneousService miscellaneousService;
    private final MiscellaneousMapper miscellaneousMapper;

    public MiscellaneousAdapterImpl(MiscellaneousService miscellaneousService,MiscellaneousMapper miscellaneousMapper) {
        this.miscellaneousService = miscellaneousService;
        this.miscellaneousMapper = miscellaneousMapper;
    }

    @Override
    public List<FAQOutputDto> getFaq() {

         return miscellaneousMapper.faqModelToDto(miscellaneousService.getFaq());
    }

    @Override
    public List<ArticleOutputDto> getAllArticles() {
        return miscellaneousMapper.articleModelToDto(miscellaneousService.getAllArticles());
    }

    @Override
    public ServicesOutputDto addServices(ServicesInputDto servicesInputDto) {

        Services services = miscellaneousMapper.servicesDtoToModel(servicesInputDto);

        return  miscellaneousMapper.servicesModelToDto(miscellaneousService.addServices(services));

    }

    @Override
    public PriceOutputDto addPrice(PriceInputDto priceInputDto, Long physicianId, Long servicesId) {
               Price price =  miscellaneousMapper.priceDtoToModel(priceInputDto);
                return miscellaneousMapper.priceModelToDto(miscellaneousService.addPrice(price , physicianId, servicesId));
    }

    @Override
    public PriceOutputDto editPrice(Long priceId, PriceInputDto priceInputDto) {
        Price price =  miscellaneousMapper.priceDtoToModel(priceInputDto);
        return miscellaneousMapper.priceModelToDto(miscellaneousService.editPrice(priceId, price));
    }

    @Override
    public List<PriceOutputDto> readPrices(Long physicianId) {
        List<PriceOutputDto> priceOutputDtos = new ArrayList<>();
        for (Price price : miscellaneousService.readPrices(physicianId)){
            priceOutputDtos.add(miscellaneousMapper.priceModelToDto(price));
        }
        return priceOutputDtos;

    }

    @Override
    public Long deletePrice(Long priceId) {
        return miscellaneousService.deletePrice(priceId);
    }
}
