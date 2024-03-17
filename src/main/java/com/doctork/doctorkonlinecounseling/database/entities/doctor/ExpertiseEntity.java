package com.doctork.doctorkonlinecounseling.database.entities.doctor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import com.doctork.doctorkonlinecounseling.domain.doctor.ExpertiseLatinNames;
import jakarta.persistence.*;


@Entity
@Table(name = "Expertises", indexes =
        {

                @Index(name = "expertise_id_index" , columnList = "expertiseId" , unique = false)

        })

public class ExpertiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "name",nullable = false)
    private String name;

    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "expertiseId", nullable = true)
    private Long Expertise;

    @Column(name = "saveDateTime", nullable = false)
    private LocalDateTime saveDateTime;

    @Column(name = "updateDateTime", nullable = true)
    private LocalDateTime updateDateTime;

    @Column(name = "latinName" , nullable = false)
    private ExpertiseLatinNames latinName;

    @ManyToMany(mappedBy = "expertises" ,fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<DoctorEntity> doctors = new HashSet<>();

    public ExpertiseEntity(Long id, String name, Long expertise, LocalDateTime saveDateTime, LocalDateTime updateDateTime, ExpertiseLatinNames latinName, Set<DoctorEntity> doctors) {
        this.id = id;
        this.name = name;
        Expertise = expertise;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.latinName = latinName;
        this.doctors = doctors;
    }

    public ExpertiseEntity() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getExpertise() {
        return Expertise;
    }

    public void setExpertise(Long expertise) {
        Expertise = expertise;
    }

    public LocalDateTime getSaveDateTime() {
        return saveDateTime;
    }

    public void setSaveDateTime(LocalDateTime saveDateTime) {
        this.saveDateTime = saveDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public ExpertiseLatinNames getLatinName() {
        return latinName;
    }

    public void setLatinName(ExpertiseLatinNames latinName) {
        this.latinName = latinName;
    }

    public Set<DoctorEntity> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<DoctorEntity> doctors) {
        this.doctors = doctors;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ExpertiseEntity that = (ExpertiseEntity) o;

        return Objects.equals(id, that.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
