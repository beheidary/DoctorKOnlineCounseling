package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.persistence.*;
@Entity
@Table(name = "socialMedia")
public class SocialMediaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "persianName")
    private String persianName;

    @Column(name = "lotinName")
    private String latinName;

    @Column(name = "state")
    private State state;

    public SocialMediaEntity(Long id, String persianName, String latinName,State state) {
        this.id = id;
        this.state = state;
        this.persianName = persianName;
        this.latinName = latinName;
    }
    public SocialMediaEntity(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public String getPersianName() {
        return persianName;
    }
    public void setPersianName(String persianName) {
        this.persianName = persianName;
    }

    public String getLatinName() {
        return latinName;
    }

    public void setLatinName(String latinName) {
        this.latinName = latinName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
