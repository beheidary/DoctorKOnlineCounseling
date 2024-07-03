package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.common.exceptions.BaseException;
import com.doctork.doctorkonlinecounseling.common.exceptions.GeneralException;
import com.doctork.doctorkonlinecounseling.common.exceptions.invalid.InvalidDataException;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.common.exceptions.temporary.DatabaseTimeOutException;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.mappers.ElasticEntityMapper;
import com.doctork.doctorkonlinecounseling.database.searchEngineRepositories.PhysicianElasticRepository;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.RabbitMqMessages.CostumeMessage;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.QueryTimeoutException;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
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
    public ElasticPhysicianEntity changeStatus(Long id, PhysicianStatus physicianStatus) {
        Optional<ElasticPhysicianEntity> optionalPhysician = physicianElasticRepository.findById(id);

        if (optionalPhysician.isPresent()) {
            ElasticPhysicianEntity existingPhysician = optionalPhysician.get();
            existingPhysician.setStatus(physicianStatus);
            return physicianElasticRepository.save(existingPhysician);
        }else{
            throw new PhysicianNotFoundException();
        }
    }

    @Override
    public ElasticPhysicianEntity changeState(Long id, State state) {
        Optional<ElasticPhysicianEntity> optionalPhysician = physicianElasticRepository.findById(id);

        if (optionalPhysician.isPresent()) {
            ElasticPhysicianEntity existingPhysician = optionalPhysician.get();
            existingPhysician.setState(state);
            return physicianElasticRepository.save(existingPhysician);
        }else{
            throw new PhysicianNotFoundException();
        }
    }


    @Override
    public PhysicianEntity addPhysician(CostumeMessage message) {
        try{

            ElasticPhysicianEntity elasticPhysicianEntity = elasticEntityMapper.messageToElasticPhysicianMapper(message);

//            if (physicianEntity.getExpertises() != null)
//                elasticPhysicianEntity.setExpertise(physicianEntity.getExpertises().iterator().next().getName());

            elasticPhysicianEntity.setId(message.getNationalCode());
            elasticPhysicianEntity.setFullName(message.getFirstName()+message.getLastName());
            elasticPhysicianEntity.setPhysicianSystemCode(message.getPhysicianSystemCode());
            elasticPhysicianEntity.setState(message.getState());
            elasticPhysicianEntity.setStatus(message.getStatus());



            elasticPhysicianEntity = physicianElasticRepository.save(elasticPhysicianEntity);

            return elasticEntityMapper.elasticPhysicianToPhysicianMapper(elasticPhysicianEntity);


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
    public Long deletePhysician(Long id) {

        physicianElasticRepository.deleteById(id);
        return id;
    }



    @Override
    public ElasticPhysicianEntity editPhysician(Long nationalCode , PhysicianEntity updatedPhysician) {
        Optional<ElasticPhysicianEntity> optionalPhysician = physicianElasticRepository.findById(nationalCode);

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
