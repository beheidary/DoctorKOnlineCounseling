package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician;

import java.time.LocalDate;

import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mongodb.lang.Nullable;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;





public class PhysicianInputDto {


    @NotNull
    private String firstName;

    @NotNull
    private String lastName;
    @NotNull
    @NotBlank
    private String nationalCode;

    private String description;
    private LocalDate dateOfBirth;
    @NotNull
    private String physicianSystemCode;
    private Gender gender;
    private String mainImage;
    private Boolean supplementaryHealthInsurance;
    @Nullable
    private String email;


    public PhysicianInputDto() {
    }


    public PhysicianInputDto(@Nullable String email, String firstName, Boolean supplementaryHealthInsurance, String lastName, String nationalCode, LocalDate dateOfBirth, String physicianSystemCode, Gender gender, String mainImage) {
        this.firstName = firstName;
        this.email = email;
        this.supplementaryHealthInsurance = supplementaryHealthInsurance;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.dateOfBirth = dateOfBirth;
        this.physicianSystemCode = physicianSystemCode;
        this.gender = gender;
        this.mainImage = mainImage;
    }

    public PhysicianInputDto(String nationalCode, String firstName, String lastName, String physicianSystemCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.physicianSystemCode = physicianSystemCode;
    }

    public Boolean getSupplementaryHealthInsurance() {
        return supplementaryHealthInsurance;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    public void setSupplementaryHealthInsurance(Boolean supplementaryHealthInsurance) {
        this.supplementaryHealthInsurance = supplementaryHealthInsurance;
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

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }
}
