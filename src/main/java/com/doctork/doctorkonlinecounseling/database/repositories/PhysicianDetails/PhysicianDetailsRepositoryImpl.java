package com.doctork.doctorkonlinecounseling.database.repositories.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails.PhysicianDetailsRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.*;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.*;
import com.doctork.doctorkonlinecounseling.database.mappers.PhysicianDetailsEntityMapper;
import com.doctork.doctorkonlinecounseling.database.mappers.PhysicianEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PhysicianDetailsRepositoryImpl implements PhysicianDetailsRepository {

    private final PhysicianDetailsEntityMapper physicianDetailsEntityMapper;
    private final EntityManager entityManager;
    private final PhysicianMySqlRepository physicianMySqlRepository;
    private final SicknessMySqlRepository sicknessMySqlRepository;
    private final PhysicianSocialMediaMySqlRepository physicianSocialMediaMySqlRepository;
    private final SocialMediaMySqlRepository socialMediaMySqlRepository;

    private final AwardsAndHonorsMySqlRepository awardsAndHonorsMySqlRepository;

    private final PhysicianEntityMapper physicianEntityMapper;

    private final EducationMySqlRepository educationMySqlRepository;
    private final ExperiencesMySqlRepository experiencesMySqlRepository;
    private final MembershipMySqlRepository membershipMySqlRepository;

    public PhysicianDetailsRepositoryImpl(AwardsAndHonorsMySqlRepository awardsAndHonorsMySqlRepository,MembershipMySqlRepository membershipMySqlRepository,PhysicianEntityMapper physicianEntityMapper,ExperiencesMySqlRepository experiencesMySqlRepository , EducationMySqlRepository educationMySqlRepository, PhysicianSocialMediaMySqlRepository physicianSocialMediaMySqlRepository,EntityManager entityManager,SocialMediaMySqlRepository socialMediaMySqlRepository,PhysicianMySqlRepository physicianMySqlRepository,PhysicianDetailsEntityMapper physicianDetailsEntityMapper,SicknessMySqlRepository sicknessMySqlRepository) {
        this.physicianDetailsEntityMapper = physicianDetailsEntityMapper;
        this.awardsAndHonorsMySqlRepository = awardsAndHonorsMySqlRepository;
        this.physicianEntityMapper = physicianEntityMapper;
        this.membershipMySqlRepository = membershipMySqlRepository;
        this.educationMySqlRepository = educationMySqlRepository;
        this.experiencesMySqlRepository = experiencesMySqlRepository;
        this.socialMediaMySqlRepository = socialMediaMySqlRepository;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.entityManager = entityManager;
        this.sicknessMySqlRepository = sicknessMySqlRepository;
        this.physicianSocialMediaMySqlRepository =  physicianSocialMediaMySqlRepository;
    }

    @Override
    public Sickness addSickness(Sickness sickness) {
        try {

            SicknessEntity sicknessEntity = physicianDetailsEntityMapper.sicknessModelToEntity(sickness);
            sicknessEntity = sicknessMySqlRepository.save(sicknessEntity);
            return physicianDetailsEntityMapper.sicknessEntityToModel(sicknessEntity);

        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public Set<Sickness> uploadSickness(String physicianId, Set<Sickness> sickness) {
    try {
        PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
        physicianEntity.setSicknesses(physicianDetailsEntityMapper.sicknessModelToEntity(sickness));
        physicianMySqlRepository.save(physicianEntity);
        return sickness;
    } catch (
    QueryTimeoutException ex) {
        throw new DatabaseTimeOutException();

    } catch (
    DataIntegrityViolationException ex) {

        throw new InvalidDataException();

    } catch (Exception ex) {

        throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

    }
    }

    @Override
    public Set<Sickness> allSicknesses(State state) {
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<SicknessEntity> cq = cb.createQuery(SicknessEntity.class);
            Root<SicknessEntity> root = cq.from(SicknessEntity.class);

            Predicate approvedStatePredicate = cb.equal(root.get("state"), state);
            cq.where(approvedStatePredicate);

            List<SicknessEntity> resultList = entityManager.createQuery(cq).getResultList();
            return physicianDetailsEntityMapper.listSicknessEntityToSetModel(resultList);

            //return physicianDetailsEntityMapper.listSicknessEntityToSetModel(sicknessMySqlRepository.findAll());

    } catch (
    QueryTimeoutException ex) {
        throw new DatabaseTimeOutException();

    } catch (
    DataIntegrityViolationException ex) {

        throw new InvalidDataException();

    } catch (Exception ex) {

        throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

    }
    }

    @Override
    @Transactional(readOnly = true)
    public Set<Sickness> allPhysicianSicknesses(String physicianId) {

        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            return physicianDetailsEntityMapper.sicknessEntityToModel(physicianEntity.getSicknesses());
        } catch (
    QueryTimeoutException ex) {
        throw new DatabaseTimeOutException();

    } catch (
    DataIntegrityViolationException ex) {

        throw new InvalidDataException();

    } catch (Exception ex) {

        throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

    }
    }

    @Override
    @Transactional(readOnly = true)
    public Set<PhysicianSocialMedia> allPhysicianSocialMedias(String physicianId) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            return physicianDetailsEntityMapper.physicianSocialMediaEntityToModel(physicianEntity.getPhysicianSocialMedia());
        } catch (
                QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public Set<SocialMedia> allSocialMedias() {
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<SocialMediaEntity> cq = cb.createQuery(SocialMediaEntity.class);
            Root<SocialMediaEntity> root = cq.from(SocialMediaEntity.class);

            Predicate approvedStatePredicate = cb.equal(root.get("state"), State.Approved);
            cq.where(approvedStatePredicate);

            List<SocialMediaEntity> resultList = entityManager.createQuery(cq).getResultList();
            return physicianDetailsEntityMapper.listSocialMediaEntityToSetModel(resultList);

        } catch (
                QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public Set<PhysicianSocialMedia> uploadSocialMedias(String physicianId, Set<PhysicianSocialMedia> physicianSocialMedia) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);

            Set<PhysicianSocialMediaEntity> newPhysicianSocialMediaEntities = physicianDetailsEntityMapper.physicianSocialMediaModelToEntity(physicianSocialMedia);

            PhysicianEntity finalPhysicianEntity = physicianEntity;
            newPhysicianSocialMediaEntities.forEach(newEntity -> {
                newEntity.setPhysician(finalPhysicianEntity);
                newEntity.setPhysicianId(physicianId);
                newEntity.setSocialMediaId(newEntity.getSocialMedia().getId());
            });

            Set<PhysicianSocialMediaEntity> currentPhysicianSocialMediaEntities = physicianEntity.getPhysicianSocialMedia();

            Set<PhysicianSocialMediaEntity> entitiesToRemove = currentPhysicianSocialMediaEntities.stream()
                    .filter(entity -> newPhysicianSocialMediaEntities.stream().noneMatch(newEntity -> newEntity.getSocialMediaId().equals(entity.getSocialMediaId())))
                    .collect(Collectors.toSet());

            entitiesToRemove.forEach(entity -> {
                entity.setPhysician(null);
                physicianSocialMediaMySqlRepository.delete(entity);
            });

            physicianEntity.getPhysicianSocialMedia().clear();
            physicianEntity.getPhysicianSocialMedia().addAll(newPhysicianSocialMediaEntities);

            physicianEntity = physicianMySqlRepository.save(physicianEntity);

            return physicianDetailsEntityMapper.physicianSocialMediaEntityToModel(physicianEntity.getPhysicianSocialMedia());
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex) {
            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    @Override
    public SocialMedia addSocialMedia(SocialMedia socialMedia) {
        try {

            SocialMediaEntity socialMediaEntity = physicianDetailsEntityMapper.socialMediaModelToEntity(socialMedia);
            socialMediaEntity = socialMediaMySqlRepository.save(socialMediaEntity);
            return physicianDetailsEntityMapper.socialMediaEntityToModel(socialMediaEntity);

        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void addEducation(String physicianId, Education education) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);

            EducationEntity educationEntity = physicianDetailsEntityMapper.educationModelToEntity(education);
            educationEntity.setPhysician(physicianEntity);
            educationEntity = educationMySqlRepository.save(educationEntity);
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public Long deleteEducation(Physician physician, Long educationId) {
        try {
        EducationEntity educationEntity = educationMySqlRepository.findEducationEntityByPhysicianAndId(physicianEntityMapper.modelToEntity(physician),educationId).orElseThrow(EducationNotfoundException::new);
        educationMySqlRepository.delete(educationEntity);
        return educationId;

        } catch (
        QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
        DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public void editEducation(Physician physician, Education education, Long educationId) {
        try {
            EducationEntity educationEntity = educationMySqlRepository.findEducationEntityByPhysicianAndId(physicianEntityMapper.modelToEntity(physician),educationId).orElseThrow(EducationNotfoundException::new);
            educationEntity.setEducationLevel(education.getEducationLevel());
            educationEntity.setEducationPlace(education.getEducationPlace());
            educationEntity.setFieldOfStudy(education.getFieldOfStudy());
            educationEntity.setGraduationDate(education.getGraduationDate());
            educationMySqlRepository.save(educationEntity);
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }

    @Override
    @Transactional(readOnly = true)
    public List<Education> allPhysicianEducations(String physicianId) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            return physicianDetailsEntityMapper.educationEntityToModel(physicianEntity.getEducations());
        }catch (
                QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void sicknessChangeState(Long sicknessId, State state) {
        try {

            SicknessEntity sicknessEntity = sicknessMySqlRepository.findById(sicknessId).orElseThrow(SicknessNotFoundException::new);
            sicknessEntity.setState(state);
            sicknessMySqlRepository.save(sicknessEntity);
        }catch (
                QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void addExperiences(String physicianId, Experiences experiences) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);

            ExperiencesEntity experiencesEntity = physicianDetailsEntityMapper.experiencesModelToEntity(experiences);
            experiencesEntity.setPhysician(physicianEntity);
            experiencesEntity = experiencesMySqlRepository.save(experiencesEntity);
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public Long deleteExperiences(Physician physician, Long experiencesId) {
        try {
            ExperiencesEntity experiencesEntity = experiencesMySqlRepository.findExperiencesEntityByPhysicianAndId(physicianEntityMapper.modelToEntity(physician),experiencesId).orElseThrow(ExperienceNotfoundException::new);
            experiencesMySqlRepository.delete(experiencesEntity);
            return experiencesId;
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public void editExperiences(Physician physician, Experiences experiences, Long experiencesId) {
        try {
            ExperiencesEntity experiencesEntity = experiencesMySqlRepository.findExperiencesEntityByPhysicianAndId(physicianEntityMapper.modelToEntity(physician),experiencesId).orElseThrow(ExperienceNotfoundException::new);
            experiencesEntity.setExperienceTitle(experiences.getExperienceTitle());
            experiencesMySqlRepository.save(experiencesEntity);
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Experiences> allPhysicianExperiences(String physicianId) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            return physicianDetailsEntityMapper.experiencesEntityToModel(physicianEntity.getExperiences());
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void addMembership(String physicianId, Membership membership) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);

            MembershipEntity membershipEntity = physicianDetailsEntityMapper.membershipModelToEntity(membership);
            membershipEntity.setPhysician(physicianEntity);
            membershipEntity = membershipMySqlRepository.save(membershipEntity);
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public Long deleteMembership(Physician physician, Long membershipId) {
        try {
            MembershipEntity membershipEntity = membershipMySqlRepository.findMembershipEntityByPhysicianAndId(physicianEntityMapper.modelToEntity(physician),membershipId).orElseThrow(MembershipNotfoundException::new);
            membershipMySqlRepository.delete(membershipEntity);
            return membershipId;
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional
    public void editMembership(Physician physician, Membership membership, Long membershipId) {
        try {
            MembershipEntity membershipEntity = membershipMySqlRepository.findMembershipEntityByPhysicianAndId(physicianEntityMapper.modelToEntity(physician),membershipId).orElseThrow(MembershipNotfoundException::new);
            membershipEntity.setMemberOf(membership.getMemberOf());
            membershipMySqlRepository.save(membershipEntity);
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<Membership> allPhysicianMemberships(String physicianId) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            return physicianDetailsEntityMapper.membershipEntityToModel(physicianEntity.getMemberships());
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void addAwardOrHonor(String physicianId, AwardsAndHonors awardsAndHonors) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);

            AwardsAndHonorsEntity awardsAndHonorsEntity = physicianDetailsEntityMapper.awardsAndHonorsModelToEntity(awardsAndHonors);
            awardsAndHonorsEntity.setPhysician(physicianEntity);
            awardsAndHonorsEntity = awardsAndHonorsMySqlRepository.save(awardsAndHonorsEntity);
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public Long deleteAwardOrHonor(Physician physician, Long awardOrHonorId) {
        try {
            AwardsAndHonorsEntity awardsAndHonorsEntity = awardsAndHonorsMySqlRepository.findAwardsAndHonorsEntityByPhysicianAndId(physicianEntityMapper.modelToEntity(physician), awardOrHonorId).orElseThrow(NotFoundException::new);
            awardsAndHonorsMySqlRepository.delete(awardsAndHonorsEntity);
            return awardOrHonorId;
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional
    public void editAwardOrHonor(Physician physician, AwardsAndHonors awardsAndHonors, Long awardOrHonorId) {
        try {
            AwardsAndHonorsEntity awardsAndHonorsEntity = awardsAndHonorsMySqlRepository.findAwardsAndHonorsEntityByPhysicianAndId(physicianEntityMapper.modelToEntity(physician), awardOrHonorId).orElseThrow(NotFoundException::new);
            awardsAndHonorsEntity.setAwardORHonorTitle(awardsAndHonors.getAwardORHonorTitle());
            awardsAndHonorsEntity.setAssociationName(awardsAndHonors.getAssociationName());
            awardsAndHonorsEntity.setYearOfReceive(awardsAndHonors.getYearOfReceive());
            awardsAndHonorsMySqlRepository.save(awardsAndHonorsEntity);
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    @Transactional(readOnly = true)
    public List<AwardsAndHonors> allPhysicianAwardsAndHonors(String physicianId) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            return physicianDetailsEntityMapper.awardsAndHonorsEntityToModel(physicianEntity.getAwardsAndHonors());
        } catch (QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();
        } catch (DataIntegrityViolationException ex) {
            throw new InvalidDataException();
        } catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


}
