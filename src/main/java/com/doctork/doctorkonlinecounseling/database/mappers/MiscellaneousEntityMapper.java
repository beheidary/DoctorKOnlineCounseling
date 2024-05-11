package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.miscellaneous.ServicesInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.ServicesOutputDto;
import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ArticleEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.PriceEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ServicesEntity;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Price;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Services;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MiscellaneousEntityMapper {

    List<Article> entityToModel(List<ArticleEntity> articleEntities);

    ServicesEntity servicesModelToEntity(Services services);

    Services servicesEntityToModel(ServicesEntity servicesEntity);

    Price priceEntityToModel(PriceEntity priceEntity);

    PriceEntity priceModelToEntity(Price price);



}
