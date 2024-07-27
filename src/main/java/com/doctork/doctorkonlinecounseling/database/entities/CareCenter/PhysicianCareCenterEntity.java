package com.doctork.doctorkonlinecounseling.database.entities.CareCenter;


import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "physician_careCenter")
@IdClass(PhysicianCareCenterId.class)
public class PhysicianCareCenterEntity {

    @Id
    @Column(name = "physician_id")
    private String physicianId;

    @Id
    @Column(name = "care_center_id")
    private Long careCenterId;

    @ManyToOne
    @JoinColumn(name = "physician_id", insertable = false, updatable = false)
    @JsonBackReference
    private PhysicianEntity physician;

    @ManyToOne
    @JoinColumn(name = "care_center_id" , insertable = false, updatable = false)
    private CareCenterEntity careCenter;

    @Column(name = "workingDays")
    private Set<WeekDay> days = new HashSet<>();

    public PhysicianCareCenterEntity() {
    }


    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    public Long getCareCenterId() {
        return careCenterId;
    }

    public void setCareCenterId(Long careCenterId) {
        this.careCenterId = careCenterId;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public CareCenterEntity getCareCenter() {
        return careCenter;
    }

    public void setCareCenter(CareCenterEntity careCenter) {
        this.careCenter = careCenter;
    }

    public Set<WeekDay> getDays() {
        return days;
    }

    public void setDays(Set<WeekDay> days) {
        this.days = days;
    }

    public int hashCode() {
        return Objects.hash(physicianId, careCenterId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PhysicianCareCenterEntity that = (PhysicianCareCenterEntity) obj;
        if (physicianId != null && careCenterId != null)
            return Objects.equals(physicianId, that.physicianId) &&
                    Objects.equals(careCenterId, that.careCenterId);
        else return super.equals(obj);
    }
}
