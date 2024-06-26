package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician;

import java.time.LocalDate;

import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;





public class PhysicianInputDto {


    @NotNull
    @NotBlank
    private String firstName;

    @NotNull
    @NotBlank
    private String lastName;
    @NotNull
    @NotBlank
    private Long nationalCode;


    @NotNull
    private String description;

    @NotNull
    @Past
    private LocalDate dateOfBirth;

    @NotNull
    private EducationLevel educationLevel;

    @NotNull
    private String physicianSystemCode;

    @NotNull
    private Gender gender;

    private String mainImage;


    public PhysicianInputDto(Long nationalCode,String mainImage,Gender gender, String description, String firstName, String lastName, LocalDate dateOfBirth, EducationLevel educationLevel, String physicianSystemCode) {
        this.firstName = firstName;
        this.mainImage = mainImage;
        this.gender = gender;
        this.nationalCode = nationalCode;
        this.description = description;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.educationLevel = educationLevel;
        this.physicianSystemCode = physicianSystemCode;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getDescription() {
        return description;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }
}
