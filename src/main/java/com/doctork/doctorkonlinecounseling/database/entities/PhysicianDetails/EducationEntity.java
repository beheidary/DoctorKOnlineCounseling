package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.EducationLevel;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "educations")
public class EducationEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "educationLevel", nullable = false)
    private EducationLevel educationLevel;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "physicianId")
    private PhysicianEntity physician;


    @Column(name = "FieldOfStudy", nullable = false)
    private String FieldOfStudy;

    @Column(name = "graduationDate" , nullable = false)
    private LocalDate graduationDate;

    @Column(name = "educationPlace" , nullable = false)
    private String educationPlace;


    public EducationEntity(Long id, EducationLevel educationLevel, PhysicianEntity physician, String fieldOfStudy, LocalDate graduationDate, String educationPlace) {
        this.id = id;
        this.educationLevel = educationLevel;
        this.physician = physician;
        this.FieldOfStudy = fieldOfStudy;
        this.graduationDate = graduationDate;
        this.educationPlace = educationPlace;
    }

    public EducationEntity(){}


    public Long getId() {
        return id;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public EducationLevel getEducationLevel() {
        return educationLevel;
    }

    public void setEducationLevel(EducationLevel educationLevel) {
        this.educationLevel = educationLevel;
    }

    public String getFieldOfStudy() {
        return FieldOfStudy;
    }

    public void setFieldOfStudy(String fieldOfStudy) {
        FieldOfStudy = fieldOfStudy;
    }

    public LocalDate getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(LocalDate graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getEducationPlace() {
        return educationPlace;
    }

    public void setEducationPlace(String educationPlace) {
        this.educationPlace = educationPlace;
    }
}
