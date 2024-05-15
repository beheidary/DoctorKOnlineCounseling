package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.mappers.ElasticEntityMapper;
import com.doctork.doctorkonlinecounseling.database.searchEngineRepositories.PhysicianElasticRepository;
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

    private final PhysicianElasticRepository physicianElasticRepository;


    public ElasticRepositoryImpl(ElasticsearchOperations elasticsearchOperations, ElasticEntityMapper elasticEntityMapper, PhysicianElasticRepository physicianElasticRepository) {
        this.elasticEntityMapper = elasticEntityMapper;
        this.physicianElasticRepository = physicianElasticRepository;
        this.elasticsearchOperations = elasticsearchOperations;
    }
    @Override
    public <T> SearchHits<T> search(Query query, Class<T> clazz) {
        return elasticsearchOperations.search(query,clazz);
    }




    @Override
    public Physician addPhysician(Physician physician) {
        try{

            ElasticPhysicianEntity physicianEntity = elasticEntityMapper.physicianToElasticPhysicianMapper(physician);

            if (physician.getExpertises() != null)
                physicianEntity.setExpertise(physician.getExpertises().iterator().next().getName());

            physicianEntity.setId(physician.getNationalCode());
            physicianEntity.setFullName(physician.getFirstName()+physician.getLastName());


            physicianEntity = physicianElasticRepository.save(physicianEntity);

            return elasticEntityMapper.elasticPhysicianToPhysicianMapper(physicianEntity);


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
    public Long deletePhysician(Long id) {

        physicianElasticRepository.deleteById(id);
        return id;
    }



    @Override
    public ElasticPhysicianEntity editPhysician(Long id , Physician updatedPhysician) {
        Optional<ElasticPhysicianEntity> optionalPhysician = physicianElasticRepository.findById(id);

        if (optionalPhysician.isPresent()) {
            ElasticPhysicianEntity existingPhysician = optionalPhysician.get();

            existingPhysician.setFullName(updatedPhysician.getFirstName()+ updatedPhysician.getLastName());
            if (updatedPhysician.getExpertises() != null)
                existingPhysician.setExpertise(updatedPhysician.getExpertises().iterator().next().getName());

            existingPhysician.setPhysicianSystemCode(updatedPhysician.getPhysicianSystemCode());
            existingPhysician.setStatus(updatedPhysician.getStatus());
            existingPhysician.setState(updatedPhysician.getState());

            return physicianElasticRepository.save(existingPhysician);
        } else {
            return null;
        }
    }




//    @Override
//    public Physician syncDoctor(PhysicianMongoEntity doctor) {

//        ElasticPhysicianfakeEntity elasticPhysicianEntity = elasticEntityMapper.mongoToElasticMapper(doctor);
//        elasticPhysicianEntity.set_id(doctor.get_id().toString());

//        return elasticEntityMapper.ElasticToDomainMapper(elasticsearchOperations.save(elasticPhysicianEntity));
//        return null;
//    }


//    @Override
//    public List<ElasticPhysicianfakeEntity> searchByRepository(String searchQuery) {
//        //return doctorElasticRepository.findByExpertiseLike(searchQuery);
//        return null;
//    }




}
