package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician;

import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;

import java.time.LocalDateTime;
import java.util.List;

public class ExpertiseOutputDto {


    private Long id;

    private String name;

    private LocalDateTime saveDateTime;
    private LocalDateTime updateDateTime;
    private ExpertiseLatinNames latinName;
    private List<PhysicianOutputDto> physicians;

    private String imageName;



    public ExpertiseOutputDto(Long id, String name, LocalDateTime saveDateTime, LocalDateTime updateDateTime, ExpertiseLatinNames latinName, List<PhysicianOutputDto> physicians,String imageName) {
        this.id = id;
        this.name = name;
        this.imageName = imageName;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.latinName = latinName;
        this.physicians = physicians;
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

    public List<PhysicianOutputDto> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(List<PhysicianOutputDto> physicians) {
        this.physicians = physicians;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
