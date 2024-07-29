package com.doctork.doctorkonlinecounseling.database.mappers;


import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.GalleryImageOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.PhysicianBankInfoOutputDto;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.*;
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

    Experiences experiencesEntityToModel(ExperiencesEntity experiencesEntity);

    List<Experiences> experiencesEntityToModel(List<ExperiencesEntity> experiencesEntities);

   ExperiencesEntity experiencesModelToEntity(Experiences experiences);

    Membership membershipEntityToModel(MembershipEntity membershipEntity);

    List<Membership> membershipEntityToModel(List<MembershipEntity> membershipEntities);

    MembershipEntity membershipModelToEntity(Membership membership);

    AwardsAndHonors awardsAndHonorsEntityToModel(AwardsAndHonorsEntity awardsAndHonorsEntity);

    List<AwardsAndHonors> awardsAndHonorsEntityToModel(List<AwardsAndHonorsEntity> awardsAndHonorsEntities);

    AwardsAndHonorsEntity awardsAndHonorsModelToEntity(AwardsAndHonors awardsAndHonors);

    List<GalleryImage> galleryImageEntityToModel (List<GalleryImageEntity> galleryImageEntities);

    //GalleryImage galleryImageEntityToModel (GalleryImageEntity galleryImageEntity);

    GalleryImageEntity galleryImageModelToEntity (GalleryImage galleryImage);

    PhysicianBankInfoEntity bankInfoModelToEntity (PhysicianBankInfo physicianBankInfo);

    PhysicianBankInfo bankInfoEntityToModel (PhysicianBankInfoEntity physicianBankInfoEntity);



}
