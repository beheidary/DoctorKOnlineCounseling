package com.doctork.doctorkonlinecounseling.UseCase.searchEngine;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import com.fasterxml.jackson.core.type.TypeReference;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SearchHitDTO;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.http.ResponseEntity;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.*;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SearchResultDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SuggestOutputDTO;
import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Component;

import java.util.*;

import co.elastic.clients.elasticsearch.core.search.FieldSuggester;
import co.elastic.clients.elasticsearch.core.search.Suggester;
import java.io.IOException;



@Component
public class ElasticServiceImpl implements ElasticService {

    private final ElasticsearchClient client;
    private final ElasticRepository elasticRepository;




    public ElasticServiceImpl(ElasticRepository elasticRepository,ElasticsearchClient client){
        this.elasticRepository = elasticRepository;
        this.client = client;
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
    public SearchHits<ElasticDoctorEntity> search(String queryString) {

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


        return elasticRepository.search(query, ElasticDoctorEntity.class);
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

    @Override
    public SearchResponse<ElasticDoctorEntity> TermSuggest (String text) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, FieldSuggester> map = new HashMap<>();
        map.put("my-suggestion", FieldSuggester.of(fs -> fs
                .term(cs -> cs
                        .field("speciality").minWordLength(3)
                )
        ));
        Suggester suggester = Suggester.of(s -> s
                .suggesters(map)
                .text(text));
        SearchRequest searchRequest = SearchRequest.of(s -> {
            s.index("doctork")
                    .source(SourceConfig.of(sc -> sc.filter(f -> f.includes(List.of("speciality")))))
                    .suggest(suggester);
        return s;
        });

        return client.search(searchRequest, ElasticDoctorEntity.class);
    }


//    public SearchResponse<ElasticDoctorEntity> autocomplete(String term, int size) throws IOException {
//        Map<String, FieldSuggester> map = new HashMap<>();
//        map.put("my-completion", FieldSuggester.of(fs -> fs
//                .completion(cs -> cs.skipDuplicates(true)
//                        .size(size)
//                        .fuzzy(SuggestFuzziness.of(sf -> sf.fuzziness("AUTO")))
//                        .field("speciality")
//                )
//        ));
//        Suggester suggester = Suggester.of(s -> s
//                .suggesters(map)
//                .text(term)
//        );
//        SearchRequest searchRequest = SearchRequest.of(s -> {
//            s.index("doctork")
//                    .source(SourceConfig.of(sc -> sc.filter(f -> f.includes(List.of("speciality")))))
//                    .suggest(suggester);
//            return s;
//        });
//        System.out.println(client.search(searchRequest, ElasticDoctorEntity.class));
//        return null;
//    }


}
