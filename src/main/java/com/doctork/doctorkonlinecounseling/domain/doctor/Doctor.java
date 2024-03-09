package com.doctork.doctorkonlinecounseling.domain.doctor;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.ExpertiseEntity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;



public class Doctor {

    private Long id;

    private String firstName;

    private String lastName;

    private LocalDate dateOfBirth;

    private String nationalCode;

    private String nationalId;

    private String mobileNumber;

    private EducationLevel educationLevel;

    private LocalDateTime registerDateTime;

    private LocalDateTime updateDateTime;

    private Set<Expertise> Expertises = new HashSet<>();

//    private UUID userId;

    private String physicianSystemCode;


    public Doctor(Long id, String firstName, String lastName, LocalDate dateOfBirth, String nationalCode, String nationalId, String mobileNumber, EducationLevel educationLevel, LocalDateTime registerDateTime, LocalDateTime updateDateTime, Set<Expertise> expertises, UUID userId, String physicianSystemCode) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.nationalCode = nationalCode;
        this.nationalId = nationalId;
        this.mobileNumber = mobileNumber;
        this.educationLevel = educationLevel;
        this.registerDateTime = registerDateTime;
        this.updateDateTime = updateDateTime;
        Expertises = expertises;
//        this.userId = userId;
        this.physicianSystemCode = physicianSystemCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public LocalDateTime getRegisterDateTime() {
        return registerDateTime;
    }

    public void setRegisterDateTime(LocalDateTime registerDateTime) {
        this.registerDateTime = registerDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public Set<Expertise> getExpertises() {
        return Expertises;
    }

    public void setExpertises(Set<Expertise> expertises) {
        Expertises = expertises;
    }

//    public UUID getUserId() {
//        return userId;
//    }

//    public void setUserId(UUID userId) {
//        this.userId = userId;
//    }

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }
}
