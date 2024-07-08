package com.doctork.doctorkonlinecounseling.domain.Expertise;

import com.doctork.doctorkonlinecounseling.domain.physician.Physician;

import java.util.ArrayList;
import java.util.List;

public class TopExpertises {

    private String name;

   private String latinName;

    private String imageName;

    private List<Physician> physicians = new ArrayList<>();

    public TopExpertises(String imageName,String name, String latinName, List<Physician> physicians) {
        this.name = name;
        this.imageName = imageName;
        this.latinName = latinName;
        this.physicians = physicians;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public List<Physician> getPhysicians() {
        return physicians;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setPhysicians(List<Physician> physicians) {
        this.physicians = physicians;
    }

}
