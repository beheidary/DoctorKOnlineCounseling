package com.doctork.doctorkonlinecounseling.UseCase.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.Infrastructure.FileManagement.MinioService.MinioService;
import com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails.PhysicianDetailsRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails.PhysicianDetailsService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.checkerframework.checker.units.qual.A;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Service
public class PhysicianDetailsServiceImpl implements PhysicianDetailsService {

    private final PhysicianDetailsRepository physicianDetailsRepository;
    private final MinioService minioService;

    public PhysicianDetailsServiceImpl(MinioService minioService,PhysicianDetailsRepository physicianDetailsRepository) {
        this.physicianDetailsRepository = physicianDetailsRepository;
        this.minioService = minioService;
    }

    @Override
    public void addSickness(Sickness sickness) {
        physicianDetailsRepository.addSickness(sickness);
    }

    @Override
    public Set<Sickness> uploadSickness(String physicianId, Set<Sickness> sicknesses) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.uploadSickness(physicianId,sicknesses);
    }

    @Override
    public Set<Sickness> allSicknesses(State state) {

        return physicianDetailsRepository.allSicknesses(state);
    }

    @Override
    public Set<Sickness> allPhysicianSicknesses(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianSicknesses(physicianId);
    }

    @Override
    public void sicknessChangeState(Long sicknessId, State state) {
        if (sicknessId == null)
            throw new IdInputException();

        physicianDetailsRepository.sicknessChangeState(sicknessId,state);
    }

    @Override
    public Set<PhysicianSocialMedia> allPhysicianSocialMedias(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianSocialMedias(physicianId);
    }

    @Override
    public Set<SocialMedia> allSocialMedias() {

        return physicianDetailsRepository.allSocialMedias();
    }

    @Override
    public Set<PhysicianSocialMedia> uploadSocialMedias(String physicianId, Set<PhysicianSocialMedia> physicianSocialMedia) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.uploadSocialMedias(physicianId,physicianSocialMedia);
    }

    @Override
    public void addSocialMedia(SocialMedia socialMedia) {
        physicianDetailsRepository.addSocialMedia(socialMedia);
    }

    @Override
    public void addEducation(String physicianId, Education education) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addEducation(physicianId,education);

    }

    @Override
    public Long deleteEducation(Physician physician, Long educationId) {
        return physicianDetailsRepository.deleteEducation(physician,educationId);
    }

    @Override
    public void editEducation(Physician physician, Education education, Long educationId) {
        physicianDetailsRepository.editEducation(physician,education,educationId);

    }

    @Override
    public List<Education> allPhysicianEducations(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianEducations(physicianId);
    }


    @Override
    public void addExperiences(String physicianId, Experiences experiences) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addExperiences(physicianId, experiences);
    }

    @Override
    public Long deleteExperiences(Physician physician, Long experiencesId) {
        return physicianDetailsRepository.deleteExperiences(physician, experiencesId);
    }

    @Override
    public void editExperiences(Physician physician, Experiences experiences, Long experiencesId) {
        physicianDetailsRepository.editExperiences(physician, experiences, experiencesId);
    }

    @Override
    public List<Experiences> allPhysicianExperiences(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianExperiences(physicianId);
    }

    @Override
    public void addMembership(String physicianId, Membership membership) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addMembership(physicianId, membership);
    }

    @Override
    public Long deleteMembership(Physician physician, Long membershipId) {
        return physicianDetailsRepository.deleteMembership(physician, membershipId);
    }

    @Override
    public void editMembership(Physician physician, Membership membership, Long membershipId) {
        physicianDetailsRepository.editMembership(physician, membership, membershipId);
    }

    @Override
    public List<Membership> allPhysicianMemberships(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianMemberships(physicianId);
    }

    @Override
    public void addAwardOrHonor(String physicianId, AwardsAndHonors awardsAndHonors) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addAwardOrHonor(physicianId, awardsAndHonors);
    }

    @Override
    public Long deleteAwardOrHonor(Physician physician, Long awardOrHonorId) {
        return physicianDetailsRepository.deleteAwardOrHonor(physician, awardOrHonorId);
    }

    @Override
    public void editAwardOrHonor(Physician physician, AwardsAndHonors awardsAndHonors, Long awardOrHonorId) {
        physicianDetailsRepository.editAwardOrHonor(physician, awardsAndHonors, awardOrHonorId);
    }

    @Override
    public List<AwardsAndHonors> allPhysicianAwardsAndHonors(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianAwardsAndHonors(physicianId);
    }

    @Override
    public List<GalleryImage> allPhysicianGalleryImages(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianGalleryImages(physicianId);
    }

    @Override
    public void addGalleryImage(String physicianId, GalleryImage galleryImage) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.addGalleryImage(physicianId,galleryImage);

    }

    @Override
    public void deActiveGalleryImage(String physicianId, Long imageId) {
        if (physicianId == null)
            throw new IdInputException();
        physicianDetailsRepository.deActiveGalleryImage(physicianId,imageId);

    }

    @Override
    public PhysicianBankInfo storeBankInfo(String physicianId, PhysicianBankInfo physicianBankInfo) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.storeBankInfo(physicianId,physicianBankInfo);
    }

    @Override
    public PhysicianBankInfo getBankInfo(String physicianId) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.getBankInfo(physicianId);
    }

    @Override
    public Article addArticle(String physicianId, Article article) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.addArticle(physicianId,article);
    }

    @Override
    public Article editArticle(String physicianId, Article article, Long articleId) throws Exception {
        if (physicianId == null || articleId == null)
            throw new IdInputException();
        Article oldArticle = physicianDetailsRepository.fetchArticle(articleId);
        Article newArticle = physicianDetailsRepository.editArticle(physicianId,article,articleId);
        if (oldArticle.getImageName() != null && !Objects.equals(oldArticle.getImageName(), newArticle.getImageName()))
            minioService.removeObject(oldArticle.getImageName());
        if (oldArticle.getFileName() != null && !Objects.equals(oldArticle.getFileName(), newArticle.getFileName()))
            minioService.removeObject(oldArticle.getFileName());
        return newArticle;
    }

    @Override
    public List<Article> allPhysicianArticles(String physicianId , State state) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allPhysicianArticles(physicianId , state);
    }

    @Override
    public List<Article> allArticles(String physicianId, State state) {
        if (physicianId == null)
            throw new IdInputException();
        return physicianDetailsRepository.allArticles(physicianId , state);
    }

    @Override
    public Long deleteArticle(String physicianId, Long articleId) throws Exception {
        if (physicianId == null || articleId == null)
            throw new IdInputException();
        Article oldArticle = physicianDetailsRepository.fetchArticle(articleId);
        articleId = physicianDetailsRepository.deleteArticle(physicianId,articleId);
        if (oldArticle.getImageName() != null)
            minioService.removeObject(oldArticle.getImageName());
        if (oldArticle.getFileName() != null)
            minioService.removeObject(oldArticle.getFileName());

        return articleId;
    }

    @Override
    public void changeArticleImage(String imageName, String physicianId, Long articleId) {
        if (physicianId == null || articleId ==null)
            throw new IdInputException();
        physicianDetailsRepository.changeArticleImage(imageName,physicianId,articleId);
    }

    @Override
    public Article changeArticleState(Long articleId, State state) {
        if (articleId == null)
            throw new IdInputException();
        return physicianDetailsRepository.changeArticleState(articleId,state);
    }

}
