package com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.api.mappers.PhysicianDetailsMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Physician.PhysicianService;
import com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails.PhysicianDetailsService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class PhysicianDetailsAdapterImpl implements PhysicianDetailsAdapter {

    private final PhysicianDetailsService physicianDetailsService;
    private final PhysicianDetailsMapper physicianDetailsMapper;
    private final PhysicianService physicianService;

    public PhysicianDetailsAdapterImpl(PhysicianService physicianService,PhysicianDetailsService physicianDetailsService, PhysicianDetailsMapper physicianDetailsMapper) {
        this.physicianDetailsService = physicianDetailsService;
        this.physicianService = physicianService;
        this.physicianDetailsMapper = physicianDetailsMapper;
    }

    @Override
    public void addSickness(String sicknessName) {

        Sickness sickness = new Sickness(null,sicknessName, State.Waiting);

        physicianDetailsService.addSickness(sickness);
    }

    @Override
    public Set<SicknessOutputDto> uploadSickness(UUID userId, Set<SicknessInputDto> sicknessInputDtos) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        Set<Sickness> sicknesses = physicianDetailsService.uploadSickness(physician.getNationalCode(),physicianDetailsMapper.sicknessDtoToModel(sicknessInputDtos));
        return physicianDetailsMapper.sicknessModelToDto(sicknesses);
    }

    @Override
    public Set<SicknessOutputDto> allSicknesses() {
        return physicianDetailsMapper.sicknessModelToDto(physicianDetailsService.allSicknesses(State.Approved));
    }

    @Override
    public Set<SicknessOutputDto> allSicknessesWithState(State state) {
        return physicianDetailsMapper.sicknessModelToDto(physicianDetailsService.allSicknesses(state));
    }

    @Override
    public Set<SicknessOutputDto> allPhysicianSicknesses(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.sicknessModelToDto(physicianDetailsService.allPhysicianSicknesses(physician.getNationalCode()));
    }

    @Override
    public void sicknessChangeState(Long sicknessId, State state) {
        physicianDetailsService.sicknessChangeState(sicknessId,state);
    }

    @Override
    public Set<PhysicianSocialMediaOutputDto> allPhysicianSocialMedias(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.physicianSocialMediaModelToDto(physicianDetailsService.allPhysicianSocialMedias(physician.getNationalCode()));
    }

    @Override
    public Set<SocialMediaOutputDto> allSocialMedias() {
        return physicianDetailsMapper.socialMediaModelToDto(physicianDetailsService.allSocialMedias());
    }

    @Override
    public Set<PhysicianSocialMediaOutputDto> uploadSocialMedias(UUID userId, Set<PhysicianSocialMediaInputDto> physicianSocialMediaInputDtos) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        Set<PhysicianSocialMedia> physicianSocialMedia = physicianDetailsService.uploadSocialMedias(physician.getNationalCode(),physicianDetailsMapper.physicianSocialMediaDtoToModel(physicianSocialMediaInputDtos));
        return physicianDetailsMapper.physicianSocialMediaModelToDto(physicianSocialMedia);
    }

    @Override
    public void addSocialMedia(String persianName, String latinName) {
        SocialMedia socialMedia = new SocialMedia(null,persianName,latinName,State.Approved);

        physicianDetailsService.addSocialMedia(socialMedia);
    }

    @Override
    public void addEducation(UUID userId, EducationInputDto educationInputDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.addEducation(physician.getNationalCode(),physicianDetailsMapper.educationDtoToModel(educationInputDto));

    }

    @Override
    public Long deleteEducation(UUID userId, Long educationId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsService.deleteEducation(physician,educationId);
    }

    @Override
    public void editEducation(UUID userId, EducationInputDto educationInputDto, Long educationId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.editEducation(physician,physicianDetailsMapper.educationDtoToModel(educationInputDto),educationId);

    }

    @Override
    public List<EducationOutputDto> allPhysicianEducations(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.educationModelToDto(physicianDetailsService.allPhysicianEducations(physician.getNationalCode()));
    }

    @Override
    public void addExperiences(UUID userId, ExperiencesInputDto experiencesInputDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.addExperiences(physician.getNationalCode(), physicianDetailsMapper.experiencesDtoToModel(experiencesInputDto));
    }

    @Override
    public Long deleteExperiences(UUID userId, Long experiencesId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsService.deleteExperiences(physician, experiencesId);
    }

    @Override
    public void editExperiences(UUID userId, ExperiencesInputDto experiencesInputDto, Long experiencesId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.editExperiences(physician, physicianDetailsMapper.experiencesDtoToModel(experiencesInputDto), experiencesId);
    }

    @Override
    public List<ExperiencesOutputDto> allPhysicianExperiences(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.experiencesModelToDto(physicianDetailsService.allPhysicianExperiences(physician.getNationalCode()));
    }
    @Override
    public void addMembership(UUID userId, MembershipInputDto membershipInputDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.addMembership(physician.getNationalCode(), physicianDetailsMapper.membershipDtoToModel(membershipInputDto));
    }

    @Override
    public Long deleteMembership(UUID userId, Long membershipId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsService.deleteMembership(physician, membershipId);
    }

    @Override
    public void editMembership(UUID userId, MembershipInputDto membershipInputDto, Long membershipId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.editMembership(physician, physicianDetailsMapper.membershipDtoToModel(membershipInputDto), membershipId);
    }

    @Override
    public List<MembershipOutputDto> allPhysicianMemberships(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.membershipModelToDto(physicianDetailsService.allPhysicianMemberships(physician.getNationalCode()));
    }

    @Override
    public void addAwardOrHonor(UUID userId, AwardsAndHonorsInputDto awardsAndHonorsInputDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.addAwardOrHonor(physician.getNationalCode(), physicianDetailsMapper.awardsAndHonorsDtoToModel(awardsAndHonorsInputDto));
    }

    @Override
    public Long deleteAwardOrHonor(UUID userId, Long awardOrHonorId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsService.deleteAwardOrHonor(physician, awardOrHonorId);
    }

    @Override
    public void editAwardOrHonor(UUID userId, AwardsAndHonorsInputDto awardsAndHonorsInputDto, Long awardOrHonorId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.editAwardOrHonor(physician, physicianDetailsMapper.awardsAndHonorsDtoToModel(awardsAndHonorsInputDto), awardOrHonorId);
    }

    @Override
    public List<AwardsAndHonorsOutputDto> allPhysicianAwardsAndHonors(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.awardsAndHonorsModelToDto(physicianDetailsService.allPhysicianAwardsAndHonors(physician.getNationalCode()));
    }

    @Override
    public List<GalleryImageOutputDto> allPhysicianGalleryImages(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.galleryImageModelToDto(physicianDetailsService.allPhysicianGalleryImages(physician.getNationalCode()));
    }

    @Override
    public void addGalleryImage(UUID userId, String imageName) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        GalleryImage galleryImage = new GalleryImage(imageName,State.Approved, Status.Active);
        physicianDetailsService.addGalleryImage(physician.getNationalCode(),galleryImage);
    }

    @Override
    public void deActiveGalleryImage(UUID userId, Long imageId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.deActiveGalleryImage(physician.getNationalCode(),imageId);
    }

    @Override
    public PhysicianBankInfoOutputDto storeBankInfo(UUID userId, PhysicianBankInfoInputDto bankInfoInputDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        PhysicianBankInfo physicianBankInfo = physicianDetailsMapper.bankInfoDtoToModel(bankInfoInputDto);

        return physicianDetailsMapper.bankInfoModelToDto(physicianDetailsService.storeBankInfo(physician.getNationalCode(),physicianBankInfo));
    }

    @Override
    public PhysicianBankInfoOutputDto getBankInfo(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);

        return physicianDetailsMapper.bankInfoModelToDto(physicianDetailsService.getBankInfo(physician.getNationalCode()));
    }

    @Override
    public ArticleOutputDto addArticle(UUID userId, ArticleInputDto articleInputDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        Article article = physicianDetailsMapper.articleDtoToModel(articleInputDto);
        article.setState(State.Waiting);
        return physicianDetailsMapper.articleModelToDto(physicianDetailsService.addArticle(physician.getNationalCode(),article));
    }

    @Override
    public ArticleOutputDto editArticle(UUID userId, ArticleInputDto articleInputDto, Long articleId) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        Article article = physicianDetailsMapper.articleDtoToModel(articleInputDto);
        article.setState(State.Waiting);
        return physicianDetailsMapper.articleModelToDto(physicianDetailsService.editArticle(physician.getNationalCode(),article,articleId));
    }

    @Override
    public List<ArticleOutputDto> allPhysicianArticles(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.articleModelToDto(physicianDetailsService.allPhysicianArticles(physician.getNationalCode() , State.Approved));
    }
    @Override
    public List<ArticleOutputDto> allArticles(UUID userId , State state) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.articleModelToDto(physicianDetailsService.allArticles(physician.getNationalCode() , state));
    }

    @Override
    public Long deleteArticle(UUID userId, Long articleId) throws Exception {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsService.deleteArticle(physician.getNationalCode(),articleId);
    }

    @Override
    public void changeArticleImage(String imageName, UUID userId, Long articleId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        physicianDetailsService.changeArticleImage(imageName,physician.getNationalCode(),articleId);
    }

    @Override
    public ArticleOutputDto changeArticleState(Long articleId, State state) {
        return physicianDetailsMapper.articleModelToDto(physicianDetailsService.changeArticleState(articleId,state));
    }

}
