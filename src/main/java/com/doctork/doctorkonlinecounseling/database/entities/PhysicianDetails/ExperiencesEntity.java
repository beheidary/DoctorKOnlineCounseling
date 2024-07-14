package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import jakarta.persistence.*;
@Entity
@Table(name = "experiences")
public class ExperiencesEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "physicianId")
    private PhysicianEntity physician;


    @Column(name = "experienceTitle", nullable = false)
    private String experienceTitle;

    public ExperiencesEntity(Long id, PhysicianEntity physician, String experienceTitle) {
        this.id = id;
        this.physician = physician;
        this.experienceTitle = experienceTitle;
    }

    public ExperiencesEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public String getExperienceTitle() {
        return experienceTitle;
    }

    public void setExperienceTitle(String experienceTitle) {
        this.experienceTitle = experienceTitle;
    }
}




