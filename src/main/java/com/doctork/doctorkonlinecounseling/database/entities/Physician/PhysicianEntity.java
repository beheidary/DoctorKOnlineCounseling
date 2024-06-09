package com.doctork.doctorkonlinecounseling.database.entities.Physician;


import com.doctork.doctorkonlinecounseling.database.entities.Expertise.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Entity
@Table(name = "physicians",indexes =
        {
                @Index(name = "first_name_index", columnList = "firstName", unique = false),
                @Index(name = "national_code_index", columnList = "nationalCode", unique = true),
        })

public class PhysicianEntity {

    @Id
    private Long nationalCode;

    @Column(name = "firstName",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "dateOfBirth",nullable = false)
    private LocalDate dateOfBirth;

    @Column(name = "businessWeight" , nullable = false)
    private Double businessWeight;

    @Column(name = "gender" , nullable = false)
    private Gender gender;

    @Column(name = "educationLevel",nullable = false)
    private EducationLevel educationLevel;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = true)
    private LocalDateTime updated_At;

    @ManyToMany(cascade = {  }, fetch = FetchType.LAZY)
    @JoinTable(name = "physician_expertise", joinColumns = @JoinColumn(name = "physician_id"), inverseJoinColumns = @JoinColumn(name = "expertise_id"))
    private Set<ExpertiseEntity> expertises = new HashSet<>();

    @Column(name = "physicianSystemCode", nullable = false)
    private String physicianSystemCode;

    @Column(name = "status", nullable = false)
    private PhysicianStatus status;

    @Column(name = "state", nullable = false)
    private State state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    @Column(name = "mainImage")
    private String mainImage;



    // Todo complete Addresses and Service Entities


    public PhysicianEntity(State state,String mainImage, Gender gender, Long nationalCode, String description, String firstName, String lastName, LocalDate dateOfBirth, Double businessWeight, EducationLevel educationLevel, LocalDateTime updated_At, Set<ExpertiseEntity> expertises, String physicianSystemCode, PhysicianStatus status, UserEntity user) {
        this.nationalCode = nationalCode;
        this.mainImage = mainImage;
        this.state = state;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.businessWeight = businessWeight;
        this.educationLevel = educationLevel;
        this.updated_At = updated_At;
        this.expertises = expertises;
        this.physicianSystemCode = physicianSystemCode;
        this.status = status;
        this.user = user;
    }

    public PhysicianEntity() {

    }
    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public String getMainImage() {
        return mainImage;
    }

    public void setMainImage(String mainImage) {
        this.mainImage = mainImage;
    }

    public void setPhysicianSystemCode(String physicianSystemCode) {
        this.physicianSystemCode = physicianSystemCode;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
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

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
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

    public Set<ExpertiseEntity> getExpertises() {
        return expertises;
    }

    public void setExpertises(Set<ExpertiseEntity> expertises) {
        this.expertises = expertises;
    }

    public PhysicianStatus getStatus() {
        return status;
    }

    public void setStatus(PhysicianStatus status) {
        this.status = status;
    }

    public Double getBusinessWeight() {
        return businessWeight;
    }

    public void setBusinessWeight(Double businessWeight) {
        this.businessWeight = businessWeight;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    //    public UUID getUserId() {
//        return userId;
//    }
//
//    public void setUserId(UUID userId) {
//        this.userId = userId;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhysicianEntity that = (PhysicianEntity) o;

        return Objects.equals(nationalCode, that.nationalCode);
    }

    @Override
    public int hashCode() {
        return nationalCode != null ? nationalCode.hashCode() : 0;
    }
}
