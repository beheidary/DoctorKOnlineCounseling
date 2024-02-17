package com.doctork.doctorkonlinecounseling.database.entities.searchEngine;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "library", type = "Doctor")
public class ElasticDoctorEntity {
    @Id
    private Long id;

    private String title;
}
