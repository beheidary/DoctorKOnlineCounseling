package com.doctork.doctorkonlinecounseling.UseCase.searchEngine;

import co.elastic.clients.elasticsearch.ElasticsearchClient;
import co.elastic.clients.elasticsearch.core.SearchRequest;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticPhysicianfakeEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.SearchHits;
import co.elastic.clients.elasticsearch.core.SearchResponse;
import co.elastic.clients.elasticsearch.core.search.*;
import com.doctork.doctorkonlinecounseling.boundary.exit.searchEngine.ElasticRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianMongoEntity;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.core.query.Query;

import java.time.Duration;
import java.util.*;

import co.elastic.clients.elasticsearch.core.search.FieldSuggester;
import co.elastic.clients.elasticsearch.core.search.Suggester;
import org.springframework.stereotype.Service;

import java.io.IOException;

import static co.elastic.clients.elasticsearch._types.SuggestMode.Missing;


@Service
public class ElasticServiceImpl implements ElasticService {

    private final ElasticsearchClient client;
    private final ElasticRepository elasticRepository;




    public ElasticServiceImpl(ElasticRepository elasticRepository,ElasticsearchClient client){
        this.elasticRepository = elasticRepository;
        this.client = client;
    }



    @Override
    public SearchHits<ElasticPhysicianfakeEntity> search(String queryString, Integer pageNumber, Integer pageSize) {

        Query query = NativeQuery.builder().withScrollTime(Duration.ofMinutes(5))
                .withQuery(q -> q.bool(
                        p -> {p.should(
                                n -> n.match(
                                        m -> m.field("expertise").query(queryString).fuzziness("AUTO")
                                )
                            );
                            p.should(
                                    n -> n.match(
                                            m -> m.field("fullName").query(queryString).fuzziness("AUTO")
                                    )
                            );
                            return p;
                        }
                )).withPageable(Pageable.ofSize(pageSize).withPage(pageNumber)).build();


        return elasticRepository.search(query, ElasticPhysicianfakeEntity.class);
    }

    @Override
    public SearchHits<ElasticPhysicianfakeEntity> expertisesSearch(String queryString) {
        Query query = NativeQuery.builder().withReactiveBatchSize(3)
                .withQuery(q -> q.bool(
                        p -> {p.should(
                                n -> n.match(
                                        m -> m.field("expertise").query(queryString).fuzziness("AUTO")
                                )
                        );
                            return p;
                        }
                )).build();


        return elasticRepository.search(query, ElasticPhysicianfakeEntity.class);
    }

    @Override
    public SearchHits<ElasticPhysicianfakeEntity> physiciansSearch(String queryString) {
        Query query = NativeQuery.builder().withReactiveBatchSize(3)
                .withQuery(q -> q.bool(
                        p -> {
                            p.should(
                                    n -> n.match(
                                            m -> m.field("fullName").query(queryString).fuzziness("AUTO")
                                    )
                            );
                            return p;
                        }
                )).build();


        return elasticRepository.search(query, ElasticPhysicianfakeEntity.class);
    }
//

    @Override
    public SearchResponse<ElasticPhysicianfakeEntity> TermSuggest (String text) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();


        Map<String, FieldSuggester> map = new HashMap<>();
        map.put("my-suggestion", FieldSuggester.of(fs -> fs
                .term(cs -> cs
                            .field("fullName").minWordLength(3).size(5).suggestMode(Missing)

                )
        ));
        map.put("my-suggestion2", FieldSuggester.of(fs -> fs
                .term(cs -> cs
                        .field("expertise").minWordLength(3).size(5).suggestMode(Missing)

                )
        ));
        Suggester suggester = Suggester.of(s -> s
                .suggesters(map)
                .text(text));
        SearchRequest searchRequest = SearchRequest.of(s -> {
            s.index("physicianfakeindex")
                    .source(SourceConfig.of(sc -> sc.filter(f -> f.includes(List.of("expertise")))))

                    .suggest(suggester);
        return s;
        });

        return client.search(searchRequest, ElasticPhysicianfakeEntity.class);
    }


    public SearchResponse<ElasticPhysicianfakeEntity> autocomplete(String term, int size) throws IOException {

        //applicable on completion Type filed s only

        Map<String, FieldSuggester> map = new HashMap<>();
        map.put("my-completion", FieldSuggester.of(fs -> fs.prefix("term")
                .completion(cs -> cs.skipDuplicates(true)
                        .size(size)
                        .fuzzy(SuggestFuzziness.of(sf -> sf.fuzziness("AUTO").minLength(4)))
                        .field("expertise")
                )
        ));
        Suggester suggester = Suggester.of(s -> s
                .suggesters(map)
        );
        SearchRequest searchRequest = SearchRequest.of(s -> {
            s.index("physicianfakeindex")
                    .source(SourceConfig.of(sc -> sc.filter(f -> f.includes(List.of("expertise")))))
                    .suggest(suggester);
            return s;
        });



        //return client.search(searchRequest, ElasticPhysicianfakeEntity.class);

        // Entity not complete

        return null;
    }

}
