package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;

import java.util.HashSet;
import java.util.Set;

public class PhysicianCareCenterOutputDto {


    private CareCenterOutputDto careCenter;

    private Set<WeekDay> days = new HashSet<>();


    public PhysicianCareCenterOutputDto(CareCenterOutputDto careCenter, Set<WeekDay> days) {
        this.careCenter = careCenter;
        this.days = days;
    }


    public CareCenterOutputDto getCareCenter() {
        return careCenter;
    }

    public void setCareCenter(CareCenterOutputDto careCenter) {
        this.careCenter = careCenter;
    }

    public Set<WeekDay> getDays() {
        return days;
    }

    public void setDays(Set<WeekDay> days) {
        this.days = days;
    }
}
