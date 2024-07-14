package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import jakarta.persistence.*;
@Entity
@Table(name = "membership")
public class MembershipEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "physicianId")
    private PhysicianEntity physician;


    @Column(name = "memberOf", nullable = false)
    private String memberOf;

    public MembershipEntity(Long id, PhysicianEntity physician, String memberOf) {
        this.id = id;
        this.physician = physician;
        this.memberOf = memberOf;
    }

    public MembershipEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public String getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(String memberOf) {
        this.memberOf = memberOf;
    }
}

