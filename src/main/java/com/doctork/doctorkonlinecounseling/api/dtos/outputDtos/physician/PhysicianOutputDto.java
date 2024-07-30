package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician;

import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class PhysicianOutputDto {

    private String firstName;
    private String lastName;
    private String description;
    private String physicianSystemCode;
    private LocalDateTime updated_At;
    private PhysicianStatus status;
    private String nationalCode;
    private Gender gender;
    private State state;
    private String mainImage;
    private String email;
    private String mobileNumber;
    private Boolean supplementaryHealthInsurance;
    private Boolean profileNecessaryInfoInserted;



    public PhysicianOutputDto(String email,String mobileNumber,Boolean supplementaryHealthInsurance,String firstName, String lastName, String description, String physicianSystemCode, LocalDateTime updated_At, PhysicianStatus status, String nationalCode, Gender gender, State state, String mainImage, Boolean profileNecessaryInfoInserted) {
        this.firstName = firstName;
        this.email = email;
        this.mobileNumber = mobileNumber;
        this.supplementaryHealthInsurance = supplementaryHealthInsurance;
        this.lastName = lastName;
        this.description = description;
        this.physicianSystemCode = physicianSystemCode;
        this.updated_At = updated_At;
        this.status = status;
        this.nationalCode = nationalCode;
        this.gender = gender;
        this.state = state;
        this.mainImage = mainImage;
        this.profileNecessaryInfoInserted = Boolean.TRUE;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getMainImage() {
        return mainImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public Boolean getSupplementaryHealthInsurance() {
        return supplementaryHealthInsurance;
    }

    public void setSupplementaryHealthInsurance(Boolean supplementaryHealthInsurance) {
        this.supplementaryHealthInsurance = supplementaryHealthInsurance;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public String getDescription() {
        return description;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getProfileNecessaryInfoInserted() {
        return profileNecessaryInfoInserted;
    }

    public void setProfileNecessaryInfoInserted(Boolean profileNecessaryInfoInserted) {
        this.profileNecessaryInfoInserted = profileNecessaryInfoInserted;
    }

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }

    public LocalDateTime getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(LocalDateTime updated_At) {
        this.updated_At = updated_At;
    }

    public PhysicianStatus getStatus() {
        return status;
    }

    public void setStatus(PhysicianStatus status) {
        this.status = status;
    }

    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }
}
