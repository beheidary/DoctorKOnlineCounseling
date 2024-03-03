package com.doctork.doctorkonlinecounseling.database.entities.searchEngine;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Document(indexName = "doctork")
public class ElasticDoctorEntity {

    @Id
    private String _idT;

    private String name;

    private String sites;

    private String nezam;

    private String speciality;



    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSites() {
        return sites;
    }

    public void setSites(String sites) {
        this.sites = sites;
    }

    public String getNezam() {
        return nezam;
    }

    public void setNezam(String nezam) {
        this.nezam = nezam;
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }


    public String get_idT() {
        return _idT;
    }

    public void set_idT(String _idT) {
        this._idT = _idT;
    }

    @Override
    public String toString() {
        return "ElasticDoctorEntity{" +
                "_id=" + _idT +
                ", full_name='" + name + '\'' +
                ", sites='" + sites + '\'' +
                ", nezam=" + nezam +
                ", speciality='" + speciality + '\'' +
                '}';
    }
}
