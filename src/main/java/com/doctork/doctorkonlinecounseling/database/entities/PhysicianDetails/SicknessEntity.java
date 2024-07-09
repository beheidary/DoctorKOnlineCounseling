package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;


import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import jakarta.persistence.*;

@Entity
@Table(name = "Sickness")
public class SicknessEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "SicknessName", nullable = false)
    private String SicknessName;

    @Column(name = "state")
    private State state;


    public SicknessEntity(Long id, String sicknessName, State state) {
        this.id = id;
        this.SicknessName = sicknessName;
        this.state = state;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SicknessEntity() {

    }
    public String getSicknessName() {
        return SicknessName;
    }

    public void setSicknessName(String sicknessName) {
        SicknessName = sicknessName;
    }
}
