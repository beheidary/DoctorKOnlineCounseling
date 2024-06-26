package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.Physician.PhysicianRepository;
import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.UserMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.PhysicianEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.*;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;


@Component
public class PhysicianRepositoryImpl implements PhysicianRepository {


    private final PhysicianEntityMapper physicianEntityMapper;
    private final PhysicianMySqlRepository physicianMySqlRepository;
    private final EntityManager em;
    private final UserMySqlRepository userMySqlRepository;




    public PhysicianRepositoryImpl(UserMySqlRepository userMySqlRepository, EntityManager em, PhysicianEntityMapper physicianEntityMapper, PhysicianMySqlRepository physicianMySqlRepository) {
        this.physicianEntityMapper = physicianEntityMapper;
        this.userMySqlRepository = userMySqlRepository;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.em = em;
    }


    @Override
    public List<Physician> topPhysician() {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PhysicianEntity> cq = cb.createQuery(PhysicianEntity.class);
        Root<PhysicianEntity> physicianEntityRoot = cq.from(PhysicianEntity.class);
        TypedQuery<PhysicianEntity> query = em.createQuery(cq);
        List<PhysicianEntity> doctorEntities = query.getResultList();
        doctorEntities.sort(Comparator.comparingDouble(PhysicianEntity::getBusinessWeight).reversed());
        doctorEntities = doctorEntities.stream().limit(3).collect(Collectors.toList());
        List<com.doctork.doctorkonlinecounseling.domain.physician.Physician> physicians = new ArrayList<>();
        for (PhysicianEntity physicianEntity : doctorEntities){
            physicians.add(physicianEntityMapper.entityToModel(physicianEntity));
        }


        return physicians;

    }

    @Override
    public Physician PhysicianCompleteProfile(Physician physician, Long nationalCode) {

        try{


            UserEntity userEntity = (UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (physicianMySqlRepository.findPhysicianEntityByNationalCode(nationalCode)== null && physicianMySqlRepository.findPhysicianEntityByUser(userEntity) == null ){

                if(Objects.equals(userEntity.getRole().toString(), "Physician")){
                    PhysicianEntity physicianEntity = physicianEntityMapper.modelToEntity(physician);
                    physicianEntity.setUser(userEntity);
                    physicianEntity = physicianMySqlRepository.save(physicianEntity);

                    userEntity = userMySqlRepository.findUserEntityById(physicianEntity.getUser().getId());
                    userEntity.setNationalCode(physicianEntity.getNationalCode());

                    userMySqlRepository.save(userEntity);

                    return physicianEntityMapper.entityToModel(physicianEntity);
                }
                else {
                    throw new PhysicianNotFoundException();
                }
            }else {
                throw new InvalidDataException();

            }
        }catch (QueryTimeoutException ex){

            throw new DatabaseTimeOutException();

        }
        catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
        catch (Exception ex){

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }






    }

    @Override
    public Physician PhysicianEditProfile(Physician physician , Long nationalCode) {

        try{

            PhysicianEntity physicianEntity = physicianMySqlRepository.findPhysicianEntityByNationalCode(nationalCode);
            if (physicianEntity == null){

                throw new PhysicianNotFoundException();

            }else {

                physicianEntity.setDateOfBirth(physician.getDateOfBirth());
                physicianEntity.setEducationLevel(physician.getEducationLevel());
                physicianEntity = physicianMySqlRepository.save(physicianEntity);

                return physicianEntityMapper.entityToModel(physicianEntity);

            }

        }catch (QueryTimeoutException ex){

            throw new DatabaseTimeOutException();

        }
        catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
        catch (Exception ex){

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }


    }




    @Override
    public Physician fetchPhysician(Long nationalCode) {


        try{

           PhysicianEntity physicianEntity = physicianMySqlRepository.findPhysicianEntityByNationalCode(nationalCode);

           if (physicianEntity == null){

               throw new PhysicianNotFoundException();

           }else{

               return physicianEntityMapper.entityToModelWithExpertise(physicianEntity);

           }

        }catch (QueryTimeoutException ex){

            throw new DatabaseTimeOutException();

        }
        catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
        catch (Exception ex){

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }


    }


    @Override
    public Physician changeStatus(Long nationalCode, PhysicianStatus status) {


        try{

            PhysicianEntity physicianEntity = physicianMySqlRepository.findPhysicianEntityByNationalCode(nationalCode);

            if (physicianEntity == null){

                throw new PhysicianNotFoundException();

            }else{

                physicianEntity.setStatus(status);
                physicianEntity = physicianMySqlRepository.save(physicianEntity);
                return physicianEntityMapper.entityToModel(physicianEntity);

            }

        }catch (QueryTimeoutException ex){

            throw new DatabaseTimeOutException();

        }
        catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
        catch (Exception ex){

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }


    }

    @Override
    public Physician changeState(Long nationalCode, State state) {

        try{

            PhysicianEntity physicianEntity = physicianMySqlRepository.findPhysicianEntityByNationalCode(nationalCode);

            if (physicianEntity == null){

                throw new PhysicianNotFoundException();

            }else{

                physicianEntity.setState(state);
                physicianEntity = physicianMySqlRepository.save(physicianEntity);
                return physicianEntityMapper.entityToModel(physicianEntity);

            }

        }catch (QueryTimeoutException ex){

            throw new DatabaseTimeOutException();

        }
        catch (DataIntegrityViolationException ex){

            throw new InvalidDataException();

        }
        catch (Exception ex){

            throw new GeneralException(1, ex.getMessage(), HttpStatus.BAD_REQUEST);

        }

    }


}



