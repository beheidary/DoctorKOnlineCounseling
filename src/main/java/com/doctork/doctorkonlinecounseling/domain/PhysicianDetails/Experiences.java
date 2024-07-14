package com.doctork.doctorkonlinecounseling.domain.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;

public class Experiences {

    private Long id;
    private String experienceTitle;

    public Experiences(Long id, String experienceTitle) {
        this.id = id;
        this.experienceTitle = experienceTitle;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getExperienceTitle() {
        return experienceTitle;
    }

    public void setExperienceTitle(String experienceTitle) {
        this.experienceTitle = experienceTitle;
    }
}
