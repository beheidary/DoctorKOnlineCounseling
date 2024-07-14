package com.doctork.doctorkonlinecounseling.domain.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;

public class AwardsAndHonors {


    private Long id;
    private String awardORHonorTitle;
    private String associationName;
    private Integer yearOfReceive;

    public AwardsAndHonors(Long id, String awardORHonorTitle, String associationName, Integer yearOfReceive) {
        this.id = id;
        this.awardORHonorTitle = awardORHonorTitle;
        this.associationName = associationName;
        this.yearOfReceive = yearOfReceive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAwardORHonorTitle() {
        return awardORHonorTitle;
    }

    public void setAwardORHonorTitle(String awardORHonorTitle) {
        this.awardORHonorTitle = awardORHonorTitle;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public Integer getYearOfReceive() {
        return yearOfReceive;
    }

    public void setYearOfReceive(Integer yearOfReceive) {
        this.yearOfReceive = yearOfReceive;
    }
}
