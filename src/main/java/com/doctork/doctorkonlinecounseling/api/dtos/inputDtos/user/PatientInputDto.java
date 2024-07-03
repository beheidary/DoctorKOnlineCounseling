package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.user;

import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class PatientInputDto {


    @NotNull
    private String nationalCode;
    @NotNull
    private Gender gender;
    @NotNull
    private String firstName;
    @NotNull
    private String lastName;
    @NotNull
    private LocalDate dateOfBirth;

    public PatientInputDto(String nationalCode, Gender gender, String firstName, String lastName, LocalDate dateOfBirth) {
        this.nationalCode = nationalCode;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }


    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
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


}
