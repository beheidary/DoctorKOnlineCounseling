package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;

public class ExperiencesOutputDto {

    private Long id;
    private String experienceTitle;

    public ExperiencesOutputDto(Long id,String experienceTitle) {
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
