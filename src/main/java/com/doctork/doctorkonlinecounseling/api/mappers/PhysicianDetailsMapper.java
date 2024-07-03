package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.PhysicianSocialMediaInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.PhysicianSocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SocialMediaInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.SocialMedia;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhysicianDetailsMapper {

    Set<SicknessOutputDto> sicknessModelToDto (Set<Sickness> sicknesses);
    Set<SocialMediaOutputDto> socialMediaModelToDto (Set<SocialMedia> socialMedia);
    Set<PhysicianSocialMediaOutputDto> physicianSocialMediaModelToDto (Set<PhysicianSocialMedia> physicianSocialMedia);

    Set<Sickness> sicknessDtoToModel (Set<SicknessInputDto> sicknessInputDtos);
    Set<PhysicianSocialMedia> physicianSocialMediaDtoToModel (Set<PhysicianSocialMediaInputDto> physicianSocialMediaInputDtos);

    Sickness sicknessDtoToModel (SicknessInputDto sicknessInputDto);

    SocialMedia socialMediaDtoToModel (SocialMediaInputDto socialMediaInputDto);
    PhysicianSocialMedia physicianSocialMediaDtoToModel (PhysicianSocialMediaInputDto physicianSocialMediaInputDto);


}
