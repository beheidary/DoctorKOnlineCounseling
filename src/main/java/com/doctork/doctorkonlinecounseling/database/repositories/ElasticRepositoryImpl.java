package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.mappers.ElasticEntityMapper;
import com.doctork.doctorkonlinecounseling.database.searchEngineRepositories.DoctorElasticRepository;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
public class ElasticRepositoryImpl implements ElasticRepository {

    private final ElasticsearchOperations elasticsearchOperations;
    private final ElasticEntityMapper elasticEntityMapper;

    private final DoctorElasticRepository doctorElasticRepository;


    public ElasticRepositoryImpl(ElasticsearchOperations elasticsearchOperations, ElasticEntityMapper elasticEntityMapper, DoctorElasticRepository doctorElasticRepository) {
        this.elasticEntityMapper = elasticEntityMapper;
        this.doctorElasticRepository = doctorElasticRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }



    @Override
    public Physician syncDoctor(PhysicianMongoEntity doctor) {

        ElasticPhysicianEntity elasticPhysicianEntity = elasticEntityMapper.mongoToElasticMapper(doctor);
        elasticPhysicianEntity.set_idT(doctor.get_id().toString());

        return elasticEntityMapper.ElasticToDomainMapper(elasticsearchOperations.save(elasticPhysicianEntity));
    }

    @Override
    public Physician addDoctor(Physician physician) {
        try{

            ElasticPhysicianEntity physicianEntity = elasticEntityMapper.DomainToElsticMapper(physician);

            //Todo maniupolate Id ond other fileds to copmatable with Elastic Entity

            physicianEntity = doctorElasticRepository.save(physicianEntity);

            return elasticEntityMapper.ElasticToDomainMapper(physicianEntity);

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
    public ElasticPhysicianEntity deleteDoctor(String id) {
        return  doctorElasticRepository.deleteBy_idT(id);
    }

    @Override
    public <T> SearchHits<T> search(Query query, Class<T> clazz) {
        return elasticsearchOperations.search(query,clazz);
    }

    @Override
    public List<ElasticPhysicianEntity> searchByRepository(String searchQuery) {
        return doctorElasticRepository.findBySpecialityLike(searchQuery);
    }

    @Override
    public ElasticPhysicianEntity editDoctor(String id , ElasticPhysicianEntity updatedDoctor) {
        Optional<ElasticPhysicianEntity> optionalDoctor = Optional.ofNullable(doctorElasticRepository.findBy_idT(id));

        if (optionalDoctor.isPresent()) {
            ElasticPhysicianEntity existingDoctor = optionalDoctor.get();

            existingDoctor.setName(updatedDoctor.getName());
            existingDoctor.setSpeciality(updatedDoctor.getSpeciality());
            existingDoctor.setSites(updatedDoctor.getSites());
            existingDoctor.setNezam(updatedDoctor.getNezam());

            return doctorElasticRepository.save(existingDoctor);
        } else {
            return null;
        }
    }


}
