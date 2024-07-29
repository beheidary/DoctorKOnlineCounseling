package com.doctork.doctorkonlinecounseling.database.entities.Physician;


import com.doctork.doctorkonlinecounseling.database.entities.CareCenter.PhysicianCareCenterEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Expertise.ExpertiseEntity;
import com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails.*;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
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
    private String nationalCode;

    @Column(name = "firstName",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;
    @Column(name = "description")
    private String description;

    @Column(name = "dateOfBirth" , nullable = true)
    private LocalDate dateOfBirth;

    @Column(name = "businessWeight" , nullable = false)
    private Double businessWeight;

    @Column(name = "gender" )
    private Gender gender;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private LocalDateTime updated_At;

    @ManyToMany(cascade = {  }, fetch = FetchType.LAZY)
    @JoinTable(name = "physician_expertise", joinColumns = @JoinColumn(name = "physician_id"), inverseJoinColumns = @JoinColumn(name = "expertise_id"))
    private Set<ExpertiseEntity> expertises = new HashSet<>();

    @ManyToMany(cascade = {  }, fetch = FetchType.LAZY)
    @JoinTable(
            name = "physician_sickness",
            joinColumns = @JoinColumn(name = "physician_id"),
            inverseJoinColumns = @JoinColumn(name = "sickess_id")
    )
    private Set<SicknessEntity> sicknesses = new HashSet<>();

    @OneToMany(mappedBy = "physician", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PhysicianSocialMediaEntity> physicianSocialMedia = new HashSet<>();


    @Column(name = "physicianSystemCode", nullable = false,unique = true)
    private String physicianSystemCode;

    @OneToMany(mappedBy = "physician", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<EducationEntity> educations ;

    @OneToMany(mappedBy = "physician", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<ExperiencesEntity> experiences ;

    @OneToMany(mappedBy = "physician", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<MembershipEntity> memberships ;

    @OneToMany(mappedBy = "physician", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<AwardsAndHonorsEntity> awardsAndHonors ;

    @OneToMany(mappedBy = "physician", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<PhysicianCareCenterEntity> physicianCareCenters = new HashSet<>();;

    @OneToMany(mappedBy = "physician", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<GalleryImageEntity> galleryImages = new HashSet<>();;

    @Column(name = "status", nullable = false)
    private PhysicianStatus status;

    @Column(name = "state", nullable = false)
    private State state;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id" , nullable = false,unique = true)
    private UserEntity user;

    @Column(name = "mainImage")
    private String mainImage;

    @Column(name = "bankAccountNumber")
    private String bankAccountNumber;
    @Column(name = "bankCardNumber")
    private String bankCardNumber;
    @Column(name = "bankShebaNumber")
    private String bankShebaNumber;






    // Todo complete Addresses and Service Entities


    public PhysicianEntity(String nationalCode,List<ExperiencesEntity> experiences,Set<PhysicianCareCenterEntity> physicianCareCenters ,List<AwardsAndHonorsEntity> awardsAndHonors,List<MembershipEntity> memberships, String firstName, String lastName, String description, LocalDate dateOfBirth, Double businessWeight, Gender gender, LocalDateTime updated_At, Set<ExpertiseEntity> expertises, Set<SicknessEntity> sicknesses, Set<PhysicianSocialMediaEntity> physicianSocialMedia,List<EducationEntity> educations, String physicianSystemCode, PhysicianStatus status, State state, UserEntity user, String mainImage,Set<GalleryImageEntity> galleryImages , String bankAccountNumber, String bankCardNumber, String bankShebaNumber) {
        this.nationalCode = nationalCode;
        this.galleryImages = galleryImages;
        this.awardsAndHonors = awardsAndHonors;
        this.firstName = firstName;
        this.lastName = lastName;
        this.experiences = experiences;
        this.memberships = memberships;
        this.description = description;
        this.dateOfBirth = dateOfBirth;
        this.businessWeight = businessWeight;
        this.gender = gender;
        this.educations = educations;
        this.updated_At = updated_At;
        this.expertises = expertises;
        this.sicknesses = sicknesses;
        this.physicianCareCenters = physicianCareCenters;
        this.physicianSystemCode = physicianSystemCode;
        this.status = status;
        this.physicianSocialMedia = physicianSocialMedia;
        this.state = state;
        this.user = user;
        this.mainImage = mainImage;
        this.bankAccountNumber = bankAccountNumber;
        this.bankCardNumber = bankCardNumber;
        this.bankShebaNumber = bankShebaNumber;
    }

    public PhysicianEntity() {

    }

    public Set<GalleryImageEntity> getGalleryImages() {
        return galleryImages;
    }

    public void setGalleryImages(Set<GalleryImageEntity> galleryImages) {
        this.galleryImages = galleryImages;
    }

    public List<EducationEntity> getEducations() {
        return educations;
    }

    public void setEducations(List<EducationEntity> educations) {
        this.educations = educations;
    }

    public Set<SicknessEntity> getSicknesses() {
        return sicknesses;
    }

    public List<AwardsAndHonorsEntity> getAwardsAndHonors() {
        return awardsAndHonors;
    }

    public void setAwardsAndHonors(List<AwardsAndHonorsEntity> awardsAndHonors) {
        this.awardsAndHonors = awardsAndHonors;
    }

    public void setSicknesses(Set<SicknessEntity> sicknesses) {
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

    public String getPhysicianSystemCode() {
        return physicianSystemCode;
    }

    public Set<PhysicianCareCenterEntity> getPhysicianCareCenters() {
        return physicianCareCenters;
    }

    public void setPhysicianCareCenters(Set<PhysicianCareCenterEntity> physicianCareCenters) {
        this.physicianCareCenters = physicianCareCenters;
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

    public String getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
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

    public List<ExperiencesEntity> getExperiences() {
        return experiences;
    }

    public void setExperiences(List<ExperiencesEntity> experiences) {
        this.experiences = experiences;
    }

    public List<MembershipEntity> getMemberships() {
        return memberships;
    }

    public void setMemberships(List<MembershipEntity> memberships) {
        this.memberships = memberships;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Set<PhysicianSocialMediaEntity> getPhysicianSocialMedia() {
        return physicianSocialMedia;
    }

    public void setPhysicianSocialMedia(Set<PhysicianSocialMediaEntity> physicianSocialMedia) {
        this.physicianSocialMedia = physicianSocialMedia;
    }
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
