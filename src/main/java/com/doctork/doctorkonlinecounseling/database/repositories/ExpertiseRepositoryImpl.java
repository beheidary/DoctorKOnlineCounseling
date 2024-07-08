package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.Duplicate.DuplicateExpertiseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.ExpertiseNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Expertise.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Expertise.RequestedExpertiseMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.ExpertiseMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.ExpertiseEntityMapper;
import com.doctork.doctorkonlinecounseling.database.mappers.PhysicianEntityMapper;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.RequestedExpertiseMongoRepository;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Expertise.RequestedExpertise;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.bson.types.ObjectId;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class ExpertiseRepositoryImpl implements ExpertiseRepository {


    private final ExpertiseMySqlRepository expertiseMySqlRepository;

    private final ExpertiseEntityMapper expertiseEntityMapper;
    private final PhysicianEntityMapper physicianEntityMapper;
    private final PhysicianMySqlRepository physicianMySqlRepository;
    private final EntityManager entityManager;

    private final ElasticRepository elasticRepository;

    private final RequestedExpertiseMongoRepository requestedExpertiseMongoRepository;



    public ExpertiseRepositoryImpl(ElasticRepository elasticRepository, PhysicianMySqlRepository physicianMySqlRepository, PhysicianEntityMapper physicianEntityMapper , EntityManager entityManager, ExpertiseMySqlRepository expertiseMySqlRepository, ExpertiseEntityMapper expertiseEntityMapper,RequestedExpertiseMongoRepository requestedExpertiseMongoRepository) {
        this.expertiseMySqlRepository = expertiseMySqlRepository;
        this.requestedExpertiseMongoRepository = requestedExpertiseMongoRepository;
        this.elasticRepository  = elasticRepository;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.physicianEntityMapper = physicianEntityMapper;
        this.entityManager =entityManager;
        this.expertiseEntityMapper = expertiseEntityMapper;
    }


    @Override
    public Expertise getExpertise(String latinName) {


        try{

            ExpertiseEntity expertiseEntity = expertiseMySqlRepository.findExpertiseEntitiesByLatinName(latinName);


            if (expertiseEntity == null){

                throw new ExpertiseNotFoundException();

            }else{

                return expertiseEntityMapper.entityToModelWithDoctor(expertiseEntity);

            }
        }catch (QueryTimeoutException ex){

            throw new DatabaseTimeOutException();

        }
        catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
        catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);
            
        }




    }

    @Override
    public Expertise editExpertise(Long expertiseId, Expertise expertise) {
        try {

            ExpertiseEntity expertiseEntity = expertiseMySqlRepository.findById(expertiseId).orElseThrow(ExpertiseNotFoundException::new);

            expertiseEntity.setImageName(expertise.getImageName());
            expertiseEntity.setName(expertise.getName());

            expertiseMySqlRepository.save(expertiseEntity);

        } catch (QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

        return null;
    }

    @Override
    public List<Expertise> getExpertises() {

        try {


            List<ExpertiseEntity> expertiseEntities = expertiseMySqlRepository.findAll();

            return expertiseEntities.stream().map(expertiseEntityMapper::entityToModelWithDoctor).collect(Collectors.toList());

        } catch (QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }


    @Override
    @Transactional(readOnly = true)
    public List<TopExpertises> findBestExpertisePhysicians() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<ExpertiseEntity> cq = cb.createQuery(ExpertiseEntity.class);
        Root<ExpertiseEntity> expertiseEntityRoot = cq.from(ExpertiseEntity.class);
        TypedQuery<ExpertiseEntity> query = entityManager.createQuery(cq);
        List<ExpertiseEntity> expertiseEntities = query.getResultList();

        List<TopExpertises> expertise = new ArrayList<>();
        for(ExpertiseEntity expertiseEntity: expertiseEntities){
            List<Physician> physicians = new ArrayList<>();
            for (PhysicianEntity physicianEntity : expertiseEntity.getPhysicians()){
                physicians.add(physicianEntityMapper.entityToModel(physicianEntity));
            }
            TopExpertises expertise1 = expertiseEntityMapper.topEntityToModel(expertiseEntity);
            physicians.sort(Comparator.comparingDouble(com.doctork.doctorkonlinecounseling.domain.physician.Physician::getBusinessWeight).reversed());
            expertise1.setPhysicians(physicians);
            expertise.add(expertise1);
        }

        return expertise;
    }

    @Override
    public Set<Expertise> uploadExpertise(String physicianId, Set<Expertise> expertise) {
        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            Set<PhysicianEntity> physicians = new HashSet<>();
            physicians.add(physicianEntity);
            for (ExpertiseEntity expertiseEntity : expertiseEntityMapper.modelToEntity(expertise)){
                expertiseEntity.setPhysicians(physicians);
                expertiseMySqlRepository.save(expertiseEntity);
            }
            //Todo change state physician
            //Todo sync with elastic
            physicianEntity.setExpertises(expertiseEntityMapper.modelToEntity(expertise));
            physicianMySqlRepository.save(physicianEntity);
            return expertise;
        } catch (
                QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

}

    @Override
    @Transactional(readOnly = true)
    public Set<Expertise> allPhysicianExpertises(String physicianId) {

        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            return expertiseEntityMapper.entityToModel(physicianEntity.getExpertises());
        } catch (
                QueryTimeoutException ex) {
            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public void requestToAddExpertise(RequestedExpertise requestedExpertise) {
        try {

            if (requestedExpertiseMongoRepository.findByName(requestedExpertise.getName()) == null){
                RequestedExpertiseMongoEntity requestedExpertiseMongoEntity = expertiseEntityMapper.mongoModelToEntity(requestedExpertise);
                requestedExpertiseMongoRepository.save(requestedExpertiseMongoEntity);
            }
            else
                throw new DuplicateExpertiseException();
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        }catch (Exception ex){
                if(ex instanceof BaseException)
                    throw ex;
                throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

            }
    }

    @Override
    public List<RequestedExpertise> waitingExpertises() {
        try {

            List<RequestedExpertiseMongoEntity> requestedExpertiseMongoEntity = requestedExpertiseMongoRepository.findByState(State.Waiting);
            return expertiseEntityMapper.mongoEntityToModel(requestedExpertiseMongoEntity);
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (
                DataIntegrityViolationException ex) {

            throw new InvalidDataException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }

    @Override
    public RequestedExpertise expertiseChangeState(RequestedExpertise requestedExpertise) {
        try {
            RequestedExpertiseMongoEntity requestedExpertiseMongoEntity = requestedExpertiseMongoRepository.findById(requestedExpertise.get_id()).orElseThrow(ExpertiseNotFoundException::new);
            if(requestedExpertiseMongoEntity.getState() == State.Waiting && (requestedExpertise.getState() == State.Rejected || requestedExpertise.getState() == State.Approved)) {
                if (requestedExpertise.getState() == State.Approved) {
                    // Todo sync with elastic
                    ExpertiseEntity expertiseEntity = new ExpertiseEntity(requestedExpertise.getName(), requestedExpertise.getLatinName());
                    expertiseEntity = expertiseMySqlRepository.save(expertiseEntity);
                }
                requestedExpertiseMongoEntity.setState(requestedExpertise.getState());
                requestedExpertiseMongoEntity.setLatinName(requestedExpertise.getLatinName());
                requestedExpertiseMongoEntity.setDescription(requestedExpertise.getDescription());
                requestedExpertise = expertiseEntityMapper.mongoEntityToModel(requestedExpertiseMongoRepository.save(requestedExpertiseMongoEntity));
                return requestedExpertise;
            }else
                throw new InvalidDataException();
        } catch (
                QueryTimeoutException ex) {

            throw new DatabaseTimeOutException();

        } catch (DataIntegrityViolationException ex) {

            throw new DuplicateExpertiseException();

        }catch (Exception ex){
            if(ex instanceof BaseException)
                throw ex;
            throw  new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }
    }
}

