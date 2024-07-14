package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails;

public class AwardsAndHonorsInputDto {

    private String awardORHonorTitle;
    private String associationName;
    private Integer yearOfReceive;


    public AwardsAndHonorsInputDto(String awardORHonorTitle, String associationName, Integer yearOfReceive) {
        this.awardORHonorTitle = awardORHonorTitle;
        this.associationName = associationName;
        this.yearOfReceive = yearOfReceive;
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
