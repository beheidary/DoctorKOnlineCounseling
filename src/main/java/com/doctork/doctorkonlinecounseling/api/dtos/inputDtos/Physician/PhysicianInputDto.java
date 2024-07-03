package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician;

import java.time.LocalDate;

import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.fasterxml.jackson.annotation.JsonFormat;
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



    private EducationLevel educationLevel;

    @NotNull
    private String physicianSystemCode;


    private Gender gender;

    private String mainImage;

    private String bankAccountNumber;
    private String bankCardNumber;
    private String bankShebaNumber;


    public PhysicianInputDto() {
    }


    public PhysicianInputDto(String firstName, String lastName, String nationalCode, String description, LocalDate dateOfBirth, EducationLevel educationLevel, String physicianSystemCode, Gender gender, String mainImage, String bankAccountNumber, String bankCardNumber, String bankShebaNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.educationLevel = educationLevel;
        this.physicianSystemCode = physicianSystemCode;
        this.gender = gender;
        this.mainImage = mainImage;
        this.bankAccountNumber = bankAccountNumber;
        this.bankCardNumber = bankCardNumber;
        this.bankShebaNumber = bankShebaNumber;
    }

    public PhysicianInputDto(String nationalCode, String firstName, String lastName, String physicianSystemCode) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.nationalCode = nationalCode;
        this.physicianSystemCode = physicianSystemCode;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankShebaNumber() {
        return bankShebaNumber;
    }

    public void setBankShebaNumber(String bankShebaNumber) {
        this.bankShebaNumber = bankShebaNumber;
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
