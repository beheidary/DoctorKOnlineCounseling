package com.doctork.doctorkonlinecounseling.api.mappers;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.*;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.springframework.stereotype.Component;

import java.util.List;
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

    Education educationDtoToModel (EducationInputDto educationInputDto);
    EducationOutputDto educationModelToDto(Education education);
    List<EducationOutputDto> educationModelToDto (List<Education> education);

    Experiences experiencesDtoToModel(ExperiencesInputDto experiencesInputDto);

    ExperiencesOutputDto experiencesModelToDto(Experiences experiences);

    List<ExperiencesOutputDto> experiencesModelToDto(List<Experiences> experiences);

    Membership membershipDtoToModel(MembershipInputDto membershipInputDto);

    MembershipOutputDto membershipModelToDto(Membership membership);

    List<MembershipOutputDto> membershipModelToDto(List<Membership> membership);

}
