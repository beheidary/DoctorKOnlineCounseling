package com.doctork.doctorkonlinecounseling.database.entities.searchEngine;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "doctorlibrary")
public class ElasticDoctorEntity {
    @Id
    private Long id;

    private String full_name;

    private String sites;

    private int nezam_code;

    private int active_count;

    private String speciality;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    public int getNezam_code() {
        return nezam_code;
    }

    public void setNezam_code(int nezam_code) {
        this.nezam_code = nezam_code;
    }

    public int getActive_count() {
        return active_count;
    }

    public void setActive_count(int active_count) {
        this.active_count = active_count;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
