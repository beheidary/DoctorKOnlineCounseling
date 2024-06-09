package com.doctork.doctorkonlinecounseling.domain.Expertise;

import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Expertise {


    private Long id;

    private String name;

    private LocalDateTime saveDateTime;

    private LocalDateTime updateDateTime;

    private ExpertiseLatinNames latinName;

    private String imageName;



    private Set<Physician> physicians = new HashSet<>();

    public Expertise(Long id,String imageName, String name, LocalDateTime saveDateTime, LocalDateTime updateDateTime, ExpertiseLatinNames latinName, Set<Physician> physicians) {
        this.id = id;
        this.imageName = imageName;
        this.name = name;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.latinName = latinName;
        this.physicians = physicians;
    }

    public Expertise() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getSaveDateTime() {
        return saveDateTime;
    }

    public void setSaveDateTime(LocalDateTime saveDateTime) {
        this.saveDateTime = saveDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public ExpertiseLatinNames getLatinName() {
        return latinName;
    }

    public void setLatinName(ExpertiseLatinNames latinName) {
        this.latinName = latinName;
    }

    public Set<Physician> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(Set<Physician> physicians) {
        this.physicians = physicians;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
