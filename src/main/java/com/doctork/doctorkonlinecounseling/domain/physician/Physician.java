package com.doctork.doctorkonlinecounseling.domain.physician;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.SicknessEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.user.User;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Physician {

    private String nationalCode;
    private String firstName;

    private String description;
    private String lastName;
    private State state;
    private LocalDate dateOfBirth;
    private LocalDateTime updated_At;
    private String physicianSystemCode;
    private Double businessWeight;
    private PhysicianStatus status;
    private User user;

    private String mainImage;

    private Gender gender;

    public Physician(String nationalCode, String firstName, String description, String lastName, State state, LocalDate dateOfBirth, LocalDateTime updated_At, String physicianSystemCode, Double businessWeight, PhysicianStatus status, User user, String mainImage, Gender gender) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.description = description;
        this.lastName = lastName;
        this.state = state;
        this.dateOfBirth = dateOfBirth;
        this.updated_At = updated_At;
        this.physicianSystemCode = physicianSystemCode;
        this.businessWeight = businessWeight;
        this.status = status;
        this.user = user;
        this.mainImage = mainImage;
        this.gender = gender;
    }

    public Physician(){
        this.state = State.Waiting;
        this.status = PhysicianStatus.Online;

    }

    public String getNationalCode() {
        return nationalCode;
    }
    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getDescription() {
        return description;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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
    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }

    public PhysicianStatus getStatus() {
        return status;
    }

    public void setStatus(PhysicianStatus status) {
        this.status = status;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Double getBusinessWeight() {
        return businessWeight;
    }

    public void setBusinessWeight(Double businessWeight) {
        this.businessWeight = businessWeight;
    }

}
