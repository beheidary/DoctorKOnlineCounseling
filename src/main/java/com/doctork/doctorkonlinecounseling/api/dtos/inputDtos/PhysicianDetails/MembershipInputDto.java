package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails;

public class MembershipInputDto {

    //    private nothing;

    private String memberOf;

    public MembershipInputDto(String memberOf,String nothing) {
//        this.nothing = nothing;
        this.memberOf = memberOf;
    }

    public String getMemberOf() {
        return memberOf;
    }

    public void setMemberOf(String memberOf) {
        this.memberOf = memberOf;
    }
}
