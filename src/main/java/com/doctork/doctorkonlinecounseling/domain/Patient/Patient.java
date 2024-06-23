package com.doctork.doctorkonlinecounseling.domain.Patient;

import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.doctork.doctorkonlinecounseling.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Patient {



    private Long nationalCode;

    private Gender gender;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private LocalDateTime updated_At;

    private User user;

    public Patient(User user, Long nationalCode, Gender gender, String firstName, String lastName, LocalDate dateOfBirth, LocalDateTime updated_At) {
        this.nationalCode = nationalCode;
        this.user = user;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.updated_At = updated_At;
    }

    public Patient(){

    }


    public Long getNationalCode() {
        return nationalCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
