package com.doctork.doctorkonlinecounseling.domain.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;

public class SocialMedia {


    private Long id;

    private String persianName;
    private String latinName;

    private State state;

    public SocialMedia(Long id, String persianName, String latinName, State state) {
        this.id = id;
        this.persianName = persianName;
        this.latinName = latinName;
        this.state = state;
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
