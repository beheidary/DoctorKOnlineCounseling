package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "awardsAndHonors")
public class AwardsAndHonorsEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "physicianId")
    private PhysicianEntity physician;


    @Column(name = "awardORHonorTitle", nullable = false)
    private String awardORHonorTitle;


    @Column(name = "associationName", nullable = false)
    private String associationName;

    @Column(name = "yearOfReceive")
    private Integer yearOfReceive;

    public AwardsAndHonorsEntity(Long id, PhysicianEntity physician, String awardORHonorTitle, String associationName, Integer yearOfReceive) {
        this.id = id;
        this.physician = physician;
        this.awardORHonorTitle = awardORHonorTitle;
        this.associationName = associationName;
        this.yearOfReceive = yearOfReceive;
    }

    public AwardsAndHonorsEntity(){}

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

    public String getAwardORHonorTitle() {
        return awardORHonorTitle;
    }

    public void setAwardORHonorTitle(String awardORHonorTitle) {
        this.awardORHonorTitle = awardORHonorTitle;
    }

    public String getAssociationName() {
        return associationName;
    }

    public void setAssociationName(String associationName) {
        this.associationName = associationName;
    }

    public Integer getYearOfReceive() {
        return yearOfReceive;
    }

    public void setYearOfReceive(Integer yearOfReceive) {
        this.yearOfReceive = yearOfReceive;
    }
}
