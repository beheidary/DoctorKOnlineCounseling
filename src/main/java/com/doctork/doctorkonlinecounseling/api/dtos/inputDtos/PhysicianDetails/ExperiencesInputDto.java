package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails;

public class ExperiencesInputDto {

//    private String nothing;

    private String experienceTitle;


    public ExperiencesInputDto(String experienceTitle, String nothing) {
//        this.nothing = nothing;
        this.experienceTitle = experienceTitle;
    }


    public String getExperienceTitle() {
        return experienceTitle;
    }

    public void setExperienceTitle(String experienceTitle) {
        this.experienceTitle = experienceTitle;
    }
}
