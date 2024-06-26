package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;

public class SicknessOutputDto {

    private Long id;

    private String SicknessName;

    private State state;

    public SicknessOutputDto(Long id, String sicknessName, State state) {
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

    public String getSicknessName() {
        return SicknessName;
    }

    public void setSicknessName(String sicknessName) {
        SicknessName = sicknessName;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
