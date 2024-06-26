package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SicknessInputDto {

    @NotNull
    @NotBlank
    private Long id;
    @NotNull
    private String SicknessName;
    @NotNull
    private State sicknessState;


    public SicknessInputDto(Long id, String sicknessName, State sicknessState) {
        this.id = id;
        SicknessName = sicknessName;
        this.sicknessState = State.Waiting;
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

    public State getSicknessState() {
        return sicknessState;
    }

    public void setSicknessState(State sicknessState) {
        this.sicknessState = sicknessState;
    }
}
