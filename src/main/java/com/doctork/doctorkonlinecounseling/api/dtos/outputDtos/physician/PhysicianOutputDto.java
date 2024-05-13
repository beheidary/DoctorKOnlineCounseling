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
    private EducationLevel educationLevel;
    private String physicianSystemCode;
    private LocalDateTime updateDateTime;
    private PhysicianStatus status;
    private Long nationalCode;
    private Gender gender;
    private State state;

    private PhysicianStatus physicianStatus;

//    private List<AddressOutputDTO> addresses;
//    private UUID userId;




    public PhysicianOutputDto(Gender gender,PhysicianStatus physicianStatus, State state, String description, String firstName, PhysicianStatus status, String lastName, EducationLevel educationLevel, LocalDateTime registerDateTime, String physicianSystemCode, LocalDateTime updateDateTime, List<AddressOutputDto> addresses, UUID userId, Long id) {
        this.firstName = firstName;
        this.state =state;
        this.physicianStatus = physicianStatus;
        this.gender = gender;
        this.description = description;
        this.lastName = lastName;
        this.status = status;
        this.educationLevel = educationLevel;
        this.physicianSystemCode = physicianSystemCode;
        this.updateDateTime = updateDateTime;
        this.nationalCode = id;
//        this.addresses = addresses;
//        this.userId = userId;
    }


    public String getFirstName() {
        return firstName;
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

    public PhysicianStatus getPhysicianStatus() {
        return physicianStatus;
    }

    public void setPhysicianStatus(PhysicianStatus physicianStatus) {
        this.physicianStatus = physicianStatus;
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

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

//    public List<AddressOutputDTO> getAddresses() {
//        return addresses;
//    }

//    public void setAddresses(List<AddressOutputDTO> addresses) {
//        this.addresses = addresses;
//    }

//    public UUID getUserId() {
//        return userId;
//    }

//    public void setUserId(UUID userId) {
//        this.userId = userId;
//    }


    public PhysicianStatus getStatus() {
        return status;
    }

    public void setStatus(PhysicianStatus status) {
        this.status = status;
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
    }
}
