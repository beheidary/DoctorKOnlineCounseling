package com.doctork.doctorkonlinecounseling.domain.PhysicianDetails;


import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Sickness {

    private Long id;

    private String SicknessName;

    private State state;

    public Sickness(Long id, String sicknessName, State state) {
        this.id = id;
        SicknessName = sicknessName;
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public String getSicknessName() {
        return SicknessName;
    }

    public void setSicknessName(String sicknessName) {
        SicknessName = sicknessName;
    }
}
