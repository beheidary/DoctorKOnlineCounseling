package com.doctork.doctorkonlinecounseling.domain.PhysicianDetails;

public class Membership {

    private Long id;

    private String memberOf;

    public Membership(Long id, String memberOf) {
        this.id = id;
        this.memberOf = memberOf;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(String memberOf) {
        this.memberOf = memberOf;
    }
}
