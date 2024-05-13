package com.doctork.doctorkonlinecounseling.domain.Expertise;

import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.util.ArrayList;
import java.util.List;

public class TopExpertises {

    private String name;

    private ExpertiseLatinNames latinName;

    private List<Physician> physicians = new ArrayList<>();

    public TopExpertises(String name, ExpertiseLatinNames latinName, List<Physician> physicians) {
        this.name = name;
        this.latinName = latinName;
        this.physicians = physicians;
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

    public List<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }

}
