package com.doctork.doctorkonlinecounseling.domain.CareCenter;

import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;

import java.util.HashSet;
import java.util.Set;

public class PhysicianCareCenter {

    private CareCenter careCenter;

    private String physicianId;

    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    private Set<WeekDay> days = new HashSet<>();


    public CareCenter getCareCenter() {
        return careCenter;
    }

    public void setCareCenter(CareCenter careCenter) {
        this.careCenter = careCenter;
    }

    public Set<WeekDay> getDays() {
        return days;
    }

    public void setDays(Set<WeekDay> days) {
        this.days = days;
    }

    public PhysicianCareCenter(CareCenter careCenter, String physicianId, Set<WeekDay> days) {
        this.careCenter = careCenter;
        this.physicianId = physicianId;
        this.days = days;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhysicianCareCenter that = (PhysicianCareCenter) o;

        if (!careCenter.equals(that.careCenter)) return false;
        return days.equals(that.days);
    }

    @Override
    public int hashCode() {
        int result = careCenter.hashCode();
        result = 31 * result + days.hashCode();
        return result;
    }

}
