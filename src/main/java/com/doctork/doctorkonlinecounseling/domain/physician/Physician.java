package com.doctork.doctorkonlinecounseling.domain.physician;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.SicknessEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.user.User;
import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
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
    private String bankAccountNumber;
    private String bankCardNumber;
    private String bankShebaNumber;
    private Set<Sickness> sicknesses;


    public Physician(Long nationalCode, String firstName, String description, String lastName, State state, LocalDate dateOfBirth, EducationLevel educationLevel, LocalDateTime updated_At, Set<Expertise> expertises, String physicianSystemCode, Double businessWeight, PhysicianStatus status, User user, String mainImage, Gender gender, String bankAccountNumber, String bankCardNumber, String bankShebaNumber, Set<Sickness> sicknesses) {
        this.nationalCode = nationalCode;
        this.firstName = firstName;
        this.description = description;
        this.lastName = lastName;
        this.state = state;
        this.dateOfBirth = dateOfBirth;
        this.educationLevel = educationLevel;
        this.updated_At = updated_At;
        this.expertises = expertises;
        this.physicianSystemCode = physicianSystemCode;
        this.businessWeight = businessWeight;
        this.status = status;
        this.user = user;
        this.mainImage = mainImage;
        this.gender = gender;
        this.bankAccountNumber = bankAccountNumber;
        this.bankCardNumber = bankCardNumber;
        this.bankShebaNumber = bankShebaNumber;
        this.sicknesses = sicknesses;
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

    public Set<Sickness> getSicknesses() {
        return sicknesses;
    }

    public void setSicknesses(Set<Sickness> sicknesses) {
        this.sicknesses = sicknesses;
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
}
