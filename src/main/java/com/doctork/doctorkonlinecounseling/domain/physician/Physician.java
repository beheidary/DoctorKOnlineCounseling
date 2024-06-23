package com.doctork.doctorkonlinecounseling.domain.physician;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.user.User;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

public class Physician {

    private Long nationalCode;
    private String firstName;

    private String description;
    private String lastName;

    private State state;
    private LocalDate dateOfBirth;
    private EducationLevel educationLevel;
    private LocalDateTime updated_At;
    private Set<Expertise> expertises = new HashSet<>();
    private String physicianSystemCode;
    private Double businessWeight;
    private PhysicianStatus status;
    private User user;

    private String mainImage;

    private Gender gender;

    public Physician(State state,String mainImage,Gender gender, Long nationalCode, String description, String firstName, Double businessWeight, String lastName, LocalDate dateOfBirth, EducationLevel educationLevel, LocalDateTime updated_At, Set<Expertise> expertises, String physicianSystemCode, PhysicianStatus status, User user) {
        this.nationalCode = nationalCode;
        this.mainImage=mainImage;
        this.state = state;
        this.gender = gender;
        this.description = description;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.educationLevel = educationLevel;
        this.updated_At = updated_At;
        this.expertises = expertises;
        this.businessWeight = businessWeight;
        this.physicianSystemCode = physicianSystemCode;
        this.status = status;
        this.user = user;
    }
    public Physician(){
        this.state = State.Waiting;
        this.status = PhysicianStatus.Online;

    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public void setNationalCode(Long nationalCode) {
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
