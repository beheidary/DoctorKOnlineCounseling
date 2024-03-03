package com.doctork.doctorkonlinecounseling.UseCase.searchEngine;

import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.database.mappers.searchEngine.ElasticEntityMapper;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;


@Component
public class ElasticServiceImpl implements ElasticService {

    private final ElasticEntityMapper elasticEntityMapper;
    private final ElasticRepository elasticRepository;


    public ElasticServiceImpl(ElasticRepository elasticRepository,ElasticEntityMapper elasticEntityMapper) {
        this.elasticRepository = elasticRepository;
        this.elasticEntityMapper = elasticEntityMapper;
    }

    @Override
    public Doctor setDoctorForSync(DoctorMongoEntity Mongodoctor) {

        return elasticRepository.syncDoctor(Mongodoctor);

    }

    @Override
    public ElasticDoctorEntity deleteDoctor(String id) {

        return elasticRepository.deleteDoctor(id);


    }

    @Override
    public List<Doctor> search(String queryString) {

        List<ElasticDoctorEntity> ret= new ArrayList<>();

        Query query = NativeQuery.builder()
                .withQuery(q -> q.bool(
                        p -> {p.should(
                                n -> n.match(
                                        m -> m.field("speciality").query(queryString).fuzziness("AUTO")
                                )
                            );
                            p.should(
                                    n -> n.match(
                                            m -> m.field("name").query(queryString).fuzziness("AUTO")
                                    )
                            );
                            return p;
                        }
                )).build();
        List<SearchHit<ElasticDoctorEntity>> response = elasticRepository.search(query, ElasticDoctorEntity.class).getSearchHits();
        for (SearchHit<ElasticDoctorEntity> results : response) {
            ret.add(results.getContent());
        }
        return elasticEntityMapper.ElasticToDomainMapper(ret);

    }

    @Override
    public Doctor addDoctor(Doctor doctor) {
        if(doctor == null)
            throw new IdInputException();

        return elasticRepository.addDoctor(doctor);


    }

    @Override
    public ElasticDoctorEntity editDoctor(String id, ElasticDoctorEntity doctor) {
        if(id == null)
            throw new IdInputException();

        return elasticRepository.editDoctor(id,doctor);
    }
}
