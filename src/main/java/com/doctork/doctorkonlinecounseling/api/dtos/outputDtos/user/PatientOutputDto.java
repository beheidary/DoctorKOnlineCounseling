package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.user;

import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientOutputDto {


    private String nationalCode;

    private Gender gender;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private LocalDateTime updated_At;

    private Boolean profileNecessaryInfoInserted;


    public PatientOutputDto(String nationalCode, Gender gender, String firstName, String lastName, LocalDate dateOfBirth,Boolean profileNecessaryInfoInserted, LocalDateTime updated_At) {
        this.nationalCode = nationalCode;
        this.profileNecessaryInfoInserted = Boolean.TRUE;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.updated_At = updated_At;
    }


    public Boolean getProfileNecessaryInfoInserted() {
        return profileNecessaryInfoInserted;
    }

    public void setProfileNecessaryInfoInserted(Boolean profileNecessaryInfoInserted) {
        this.profileNecessaryInfoInserted = profileNecessaryInfoInserted;
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

    public LocalDateTime getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(LocalDateTime updated_At) {
        this.updated_At = updated_At;
    }



}
