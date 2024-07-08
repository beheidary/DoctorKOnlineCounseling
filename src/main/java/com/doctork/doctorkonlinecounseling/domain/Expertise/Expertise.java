package com.doctork.doctorkonlinecounseling.domain.Expertise;

import com.doctork.doctorkonlinecounseling.domain.physician.Physician;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Expertise {


    private Long id;

    private String name;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

       private String latinName;

    private String imageName;



    private Set<Physician> physicians = new HashSet<>();

    public Expertise(Long id,String imageName, String name, LocalDateTime createdAt, LocalDateTime updatedAt, String latinName, Set<Physician> physicians) {
        this.id = id;
        this.imageName = imageName;
        this.name = name;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
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
