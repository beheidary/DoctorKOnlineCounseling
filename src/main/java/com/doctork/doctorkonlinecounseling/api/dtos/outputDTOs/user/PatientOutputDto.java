package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.user;

import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class PatientOutputDto {


    private Long nationalCode;

    private Gender gender;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private LocalDateTime updated_At;

    public PatientOutputDto(Long nationalCode, Gender gender, String firstName, String lastName, LocalDate dateOfBirth, LocalDateTime updated_At) {
        this.nationalCode = nationalCode;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.updated_At = updated_At;
    }


    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
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
