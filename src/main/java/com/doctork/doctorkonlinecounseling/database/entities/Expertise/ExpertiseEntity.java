package com.doctork.doctorkonlinecounseling.database.entities.Expertise;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;


@Entity
@Table(name = "expertises", indexes =
        {

              //  @Index(name = "expertise_id_index" , columnList = "expertiseId" , unique = false)

        } , uniqueConstraints =  {@UniqueConstraint(columnNames = {"latinName", "name"})})

public class ExpertiseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name",nullable = false , unique = true)
    private String name;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
    @UpdateTimestamp
    @Column(name = "updated_at", nullable = true)
    private LocalDateTime updatedAt;

    @Column(name = "latinName" , nullable = false , unique = true)
       private String latinName;

    @ManyToMany(mappedBy = "expertises" ,fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private Set<PhysicianEntity> physicians = new HashSet<>();

    @Column(name = "imageName" , nullable = true)
    private String imageName;





    public ExpertiseEntity(String imageName, Long id, String name, Long expertise, LocalDateTime createdAt, LocalDateTime updatedAt, String latinName, Set<PhysicianEntity> physicians) {
        this.id = id;
        this.imageName = imageName;
        this.name = name;
//        Expertise = expertise;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.latinName = latinName;
        this.physicians = physicians;
    }

    public ExpertiseEntity(String name , String latinName) {
        this.name = name;
        this.latinName = latinName;
        this.createdAt = LocalDateTime.now();

    }
    public ExpertiseEntity() {
        this.createdAt = LocalDateTime.now();

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


    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
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
