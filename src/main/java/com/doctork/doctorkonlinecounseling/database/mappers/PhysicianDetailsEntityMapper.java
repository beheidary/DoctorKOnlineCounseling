package com.doctork.doctorkonlinecounseling.database.mappers;


import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.EducationEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.PhysicianSocialMediaEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.SicknessEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.SocialMediaEntity;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Education;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.SocialMedia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicianDetailsEntityMapper {

    Sickness sicknessEntityToModel (SicknessEntity sicknessEntity);
    SicknessEntity sicknessModelToEntity (Sickness sickness);
    Set<Sickness> sicknessEntityToModel (Set<SicknessEntity> sicknessEntities);
    @Mappings({@Mapping(target = "physician",expression = "java(null)")})
    Set<PhysicianSocialMedia> physicianSocialMediaEntityToModel (Set<PhysicianSocialMediaEntity> physicianSocialMediaEntities);
    Set<Sickness> listSicknessEntityToSetModel(List<SicknessEntity> sicknessEntities);
    Set<SocialMedia> listSocialMediaEntityToSetModel(List<SocialMediaEntity> socialMediaEntities);
    Set <SicknessEntity> sicknessModelToEntity (Set<Sickness> sicknesses);
    Set <PhysicianSocialMediaEntity> physicianSocialMediaModelToEntity (Set<PhysicianSocialMedia> physicianSocialMedia);

    SocialMediaEntity socialMediaModelToEntity(SocialMedia socialMedia);

    SocialMedia socialMediaEntityToModel(SocialMediaEntity socialMediaEntity);

    Education educationEntityToModel(EducationEntity educationEntity);
    List<Education> educationEntityToModel(List<EducationEntity> educationEntity);

    EducationEntity educationModelToEntity (Education education);


}
