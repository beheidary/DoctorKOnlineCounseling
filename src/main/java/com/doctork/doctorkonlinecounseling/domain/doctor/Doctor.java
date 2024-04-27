package com.doctork.doctorkonlinecounseling.domain.doctor;
import com.doctork.doctorkonlinecounseling.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

public class Doctor {

    private Long nationalCode;
    private String firstName;
    private String lastName;
    private LocalDate dateOfBirth;
    private EducationLevel educationLevel;
    private LocalDateTime updated_At;
    private Set<Expertise> expertises = new HashSet<>();
    private String physicianSystemCode;
    private DoctorStatus status;
    private User user;

    public Doctor(Long nationalCode, String firstName, String lastName, LocalDate dateOfBirth, EducationLevel educationLevel, LocalDateTime updated_At, Set<Expertise> expertises, String physicianSystemCode, DoctorStatus status, User user) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.educationLevel = educationLevel;
        this.updated_At = updated_At;
        this.expertises = expertises;
        this.physicianSystemCode = physicianSystemCode;
        this.status = status;
        this.user = user;
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
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

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public LocalDateTime getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(LocalDateTime updated_At) {
        this.updated_At = updated_At;
    }

    public Set<Expertise> getExpertises() {
        return expertises;
    }

    public void setExpertises(Set<Expertise> expertises) {
        this.expertises = expertises;
    }

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }

    public DoctorStatus getStatus() {
        return status;
    }

    public void setStatus(DoctorStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
