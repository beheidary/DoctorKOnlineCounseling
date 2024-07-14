package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician;


import java.time.LocalDateTime;
import java.util.List;

public class ExpertiseOutputDto {


    private Long id;

    private String name;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String latinName;
    private String imageName;



    public ExpertiseOutputDto(Long id, String name, LocalDateTime createdAt, LocalDateTime updatedAt, String latinName,String imageName) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.latinName = latinName;
    }


    public ExpertiseOutputDto() {

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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
