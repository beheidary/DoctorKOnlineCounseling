package com.doctork.doctorkonlinecounseling.database.repositories;

import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.database.mappers.searchEngine.ElasticEntityMapper;
import com.doctork.doctorkonlinecounseling.database.searchEngineRepositories.DoctorElasticRepository;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
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
    public Doctor syncDoctor(DoctorMongoEntity doctor) {

        ElasticDoctorEntity elasticDoctorEntity = elasticEntityMapper.mongoToElasticMapper(doctor);
        elasticDoctorEntity.set_idT(doctor.get_id().toString());
        return elasticEntityMapper.ElasticToDomainMapper(elasticsearchOperations.save(elasticDoctorEntity));
    }


    @Override
    public ElasticDoctorEntity deleteDoctor(String id) {
        return  doctorElasticRepository.deleteBy_idT(id);
    }

    @Override
    public <T> SearchHits<T> search(Query query, Class<T> clazz) {
        return elasticsearchOperations.search(query,clazz);
    }

    @Override
    public List<ElasticDoctorEntity> searchByRepository(String searchQuery) {
        return doctorElasticRepository.findBySpecialityLike(searchQuery);
    }

    @Override
    public ElasticDoctorEntity editDoctor(String id , ElasticDoctorEntity updatedDoctor) {
        Optional<ElasticDoctorEntity> optionalDoctor = Optional.ofNullable(doctorElasticRepository.findBy_idT(id));

        if (optionalDoctor.isPresent()) {
            ElasticDoctorEntity existingDoctor = optionalDoctor.get();

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
