package com.doctork.doctorkonlinecounseling.database.entities.Expertise;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "expertises", indexes =
        {

              //  @Index(name = "expertise_id_index" , columnList = "expertiseId" , unique = false)

        })

public class ExpertiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",nullable = false)
    private String name;
    @CreationTimestamp
    @Column(name = "saveDateTime", nullable = false)
    private LocalDateTime saveDateTime;
    @UpdateTimestamp
    @Column(name = "updateDateTime", nullable = true)
    private LocalDateTime updateDateTime;

    @Column(name = "latinName" , nullable = false)
    private ExpertiseLatinNames latinName;

    @ManyToMany(mappedBy = "expertises" ,fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<PhysicianEntity> physicians = new HashSet<>();

    @Column(name = "imageName" , nullable = true)
    private String imageName;





    public ExpertiseEntity(String imageName, Long id, String name, Long expertise, LocalDateTime saveDateTime, LocalDateTime updateDateTime, ExpertiseLatinNames latinName, Set<PhysicianEntity> physicians) {
        this.id = id;
        this.imageName = imageName;
        this.name = name;
//        Expertise = expertise;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.latinName = latinName;
        this.physicians = physicians;
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

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }

    public void setName(String name) {
        this.name = name;
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

    public Set<PhysicianEntity> getPhysicians() {
        return physicians;
    }

    public void setPhysicians(Set<PhysicianEntity> physicians) {
        this.physicians = physicians;
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
