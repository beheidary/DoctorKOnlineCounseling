package com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Education;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.GalleryImage;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianBankInfo;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface PhysicianDetailsAdapter {

    void addSickness (String sicknessName);
    Set<SicknessOutputDto> uploadSickness (UUID userId,Set<SicknessInputDto> sicknessInputDtos);

    Set<SicknessOutputDto> allSicknesses ();
    Set<SicknessOutputDto> allSicknessesWithState (State state);
    Set<SicknessOutputDto> allPhysicianSicknesses (UUID userId);

    void sicknessChangeState (Long sicknessId , State state);


    Set<PhysicianSocialMediaOutputDto> allPhysicianSocialMedias(UUID userId);

    Set<SocialMediaOutputDto> allSocialMedias();
    Set<PhysicianSocialMediaOutputDto> uploadSocialMedias (UUID userId, Set<PhysicianSocialMediaInputDto> physicianSocialMediaInputDtos);
    void addSocialMedia(String persianName , String latinName);

    void addEducation (UUID userId, EducationInputDto educationInputDto);

    Long deleteEducation ( UUID userId,Long educationId);

    void editEducation ( UUID userId, EducationInputDto educationInputDto ,Long educationId );

    List<EducationOutputDto> allPhysicianEducations(UUID userId);


    void addExperiences(UUID userId, ExperiencesInputDto experiencesInputDto);

    Long deleteExperiences(UUID userId, Long experiencesId);

    void editExperiences(UUID userId, ExperiencesInputDto experiencesInputDto, Long experiencesId);

    List<ExperiencesOutputDto> allPhysicianExperiences(UUID userId);



    void addMembership(UUID userId, MembershipInputDto membershipInputDto);

    Long deleteMembership(UUID userId, Long membershipId);

    void editMembership(UUID userId, MembershipInputDto membershipInputDto, Long membershipId);

    List<MembershipOutputDto> allPhysicianMemberships(UUID userId);

    void addAwardOrHonor(UUID userId, AwardsAndHonorsInputDto awardsAndHonorsInputDto);

    Long deleteAwardOrHonor(UUID userId, Long awardOrHonorId);

    void editAwardOrHonor(UUID userId, AwardsAndHonorsInputDto awardsAndHonorsInputDto, Long awardOrHonorId);

    List<AwardsAndHonorsOutputDto> allPhysicianAwardsAndHonors(UUID userId);

    List<GalleryImageOutputDto> allPhysicianGalleryImages(UUID userId);
    void addGalleryImage (UUID userId, String imageName);
    void deActiveGalleryImage (UUID userId, Long imageId);

    PhysicianBankInfoOutputDto storeBankInfo (UUID userId, PhysicianBankInfoInputDto bankInfoInputDto);
    PhysicianBankInfoOutputDto getBankInfo (UUID userId);

}
