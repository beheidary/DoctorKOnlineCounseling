package com.doctork.doctorkonlinecounseling.database.entities.CareCenter;


import jakarta.persistence.*;

@Table(name = "provinceCities")
@Entity
public class provinceCitiesEntity {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "parent")
    private Integer parent;

    @Column(name = "title")
    private String title;

    public provinceCitiesEntity(Long id, Integer parent, String title) {
        this.id = id;
        this.parent = parent;
        this.title = title;
    }

    public provinceCitiesEntity(){

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
