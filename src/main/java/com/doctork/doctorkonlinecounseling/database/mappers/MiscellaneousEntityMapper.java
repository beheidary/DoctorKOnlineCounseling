package com.doctork.doctorkonlinecounseling.database.mappers;

import com.doctork.doctorkonlinecounseling.database.entities.Miscellaneous.ArticleEntity;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.Article;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MiscellaneousEntityMapper {

    List<Article> entityToModel(List<ArticleEntity> articleEntities);

}
