package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician;


import java.time.LocalDateTime;

public class ExpertiseInputDto {


    private Long id;

    private String name;

    private String latinName;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private String imageName;


    public ExpertiseInputDto(Long id, String name, String latinName, LocalDateTime createdAt, LocalDateTime updatedAt, String imageName) {
        this.id = id;
        this.name = name;
        this.latinName = latinName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.imageName = imageName;
    }
    public ExpertiseInputDto(Long id, String name){
        this.id = id;
        this.name = name;
    }
    public ExpertiseInputDto(){

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

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
