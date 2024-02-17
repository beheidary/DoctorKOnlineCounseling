package com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor;

import com.doctork.doctorkonlinecounseling.domain.doctor.EducationLevel;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class DoctorOutputDTO {





    private String firstName;
    private String lastName;
    private EducationLevel educationLevel;
    private LocalDateTime registerDateTime;

    private String physicianSystemCode;
    private LocalDateTime updateDateTime;
    private List<AddressOutputDTO> addresses;
    private UUID userId;
    private Long id;

    public DoctorOutputDTO(Long id, String firstName, String lastName, EducationLevel educationLevel, LocalDateTime registerDateTime, String physicianSystemCode, LocalDateTime updateDateTime, List<AddressOutputDTO> addresses, UUID userId) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.educationLevel = educationLevel;
        this.registerDateTime = registerDateTime;
        this.physicianSystemCode = physicianSystemCode;
        this.updateDateTime = updateDateTime;
        this.addresses = addresses;
        this.userId = userId;
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

    public List<AddressOutputDTO> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<AddressOutputDTO> addresses) {
        this.addresses = addresses;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }


}
