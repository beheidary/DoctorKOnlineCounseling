package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Price.PriceEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Price.ServicesEntity;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.Price.Services;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PriceEntityMapper {


    ServicesEntity servicesModelToEntity(Services services);

    Services servicesEntityToModel(ServicesEntity servicesEntity);

    Price priceEntityToModel(PriceEntity priceEntity);

    PriceEntity priceModelToEntity(Price price);

    List<Services> servicesEntityToModel (List<ServicesEntity> servicesEntities);
    List<Price> priceEntityToModel (List<PriceEntity> priceEntities);

}
