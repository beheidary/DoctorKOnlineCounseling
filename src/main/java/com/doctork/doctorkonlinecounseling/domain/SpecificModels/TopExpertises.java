package com.doctork.doctorkonlinecounseling.domain.SpecificModels;

import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.util.ArrayList;
import java.util.List;

public class TopExpertises {

    private String name;

    private ExpertiseLatinNames latinName;

    private List<Doctor> doctors = new ArrayList<>();

    public TopExpertises(String name, ExpertiseLatinNames latinName, List<Doctor> doctors) {
        this.name = name;
        this.latinName = latinName;
        this.doctors = doctors;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExpertiseLatinNames getLatinName() {
        return latinName;
    }

    public void setLatinName(ExpertiseLatinNames latinName) {
        this.latinName = latinName;
    }

    public List<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(List<Doctor> doctors) {
        this.doctors = doctors;
    }

}
