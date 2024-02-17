package com.doctork.doctorkonlinecounseling.database.entities.doctor;


import com.doctork.doctorkonlinecounseling.domain.doctor.EducationLevel;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Entity
@Table(name = "Doctors",indexes =
        {
                @Index(name = "first_name_index", columnList = "firstName", unique = false),
                @Index(name = "national_code_index", columnList = "nationalCode", unique = true),
                @Index(name = "national_id_index", columnList = "nationalId", unique = true),
                @Index(name = "mobile_number_index", columnList = "mobileNumber", unique = false),
                @Index(name = "user_id_index", columnList = "userId", unique = false)
        })

public class DoctorEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "firstName",nullable = false)
    private String firstName;


    @Column(name = "lastName",nullable = false)
    private String lastName;


    @Column(name = "dateOfBirth",nullable = false)
    private LocalDate dateOfBirth;


    @Column(name = "nationalCode",nullable = false)
    private String nationalCode;


    @Column(name = "nationalId",nullable = false)
    private String nationalId;



    @Column(name = "mobileNumber",nullable = false)
    private String mobileNumber;


    @Column(name = "educationLevel",nullable = false)
    private EducationLevel educationLevel;

    @Column(name = "registerDateTime",nullable = false)
    private LocalDateTime registerDateTime;


    @Column(name = "updateDateTime",nullable = true)
    private LocalDateTime updateDateTime;


    @ManyToMany(mappedBy = "doctors", fetch = FetchType.EAGER)
    private List<ExpertiseEntity> Expertises;


    @Column(name = "userId", nullable = false)
    private UUID userId;


    // Todo complete Addresses and Service Entities
//
//    @ManyToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private List<ServiceEntity> services;
//
//
//    @ManyToMany(mappedBy = "doctor", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
//    private List<AddressEntity> addresses;
//


    public DoctorEntity(Long id, String firstName, String lastName, LocalDate dateOfBirth, String nationalCode, String nationalId, String mobileNumber, EducationLevel educationLevel, LocalDateTime registerDateTime, LocalDateTime updateDateTime, List<ExpertiseEntity> expertises, UUID userId) {
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
        this.userId = userId;
    }

    public DoctorEntity() {

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

    public List<ExpertiseEntity> getExpertises() {
        return Expertises;
    }

    public void setExpertises(List<ExpertiseEntity> expertises) {
        Expertises = expertises;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoctorEntity that = (DoctorEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
