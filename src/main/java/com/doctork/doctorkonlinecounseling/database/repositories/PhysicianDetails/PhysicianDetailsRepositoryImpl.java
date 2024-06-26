package com.doctork.doctorkonlinecounseling.database.repositories.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.boundary.exit.PhysicianDetails.PhysicianDetailsRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.SicknessEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.PhysicianMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.SicknessMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mappers.PhysicianDetailsEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class PhysicianDetailsRepositoryImpl implements PhysicianDetailsRepository {

    private final PhysicianDetailsEntityMapper physicianDetailsEntityMapper;
    private final EntityManager entityManager;
    private final PhysicianMySqlRepository physicianMySqlRepository;
    private final SicknessMySqlRepository sicknessMySqlRepository;

    public PhysicianDetailsRepositoryImpl(EntityManager entityManager,PhysicianMySqlRepository physicianMySqlRepository,PhysicianDetailsEntityMapper physicianDetailsEntityMapper,SicknessMySqlRepository sicknessMySqlRepository) {
        this.physicianDetailsEntityMapper = physicianDetailsEntityMapper;
        this.physicianMySqlRepository = physicianMySqlRepository;
        this.entityManager = entityManager;
        this.sicknessMySqlRepository = sicknessMySqlRepository;
    }

    @Override
    public Sickness crateSickness(Sickness sickness) {
        try {

            SicknessEntity sicknessEntity = physicianDetailsEntityMapper.sicknessModelToEntity(sickness);
            sicknessEntity = sicknessMySqlRepository.save(sicknessEntity);
            return null;

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
    public Set<Sickness> uploadSickness(Long physicianId, Set<Sickness> sickness) {
    try {
        PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
        physicianEntity.setSicknessEntities(physicianDetailsEntityMapper.sicknessModelToEntity(sickness));
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
    public Set<Sickness> allSicknesses() {
        try {

            CriteriaBuilder cb = entityManager.getCriteriaBuilder();
            CriteriaQuery<SicknessEntity> cq = cb.createQuery(SicknessEntity.class);
            Root<SicknessEntity> root = cq.from(SicknessEntity.class);

            Predicate approvedStatePredicate = cb.equal(root.get("state"), "Approved");
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
    public Set<Sickness> allPhysicianSicknesses(Long physicianId) {

        try {
            PhysicianEntity physicianEntity = physicianMySqlRepository.findById(physicianId).orElseThrow(PhysicianNotFoundException::new);
            return physicianDetailsEntityMapper.sicknessEntityToModel(physicianEntity.getSicknessEntities());
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

}
