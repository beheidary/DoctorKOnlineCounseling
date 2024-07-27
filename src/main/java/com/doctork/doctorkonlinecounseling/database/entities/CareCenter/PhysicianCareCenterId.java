package com.doctork.doctorkonlinecounseling.database.entities.CareCenter;

import java.io.Serializable;
import java.util.Objects;

public class PhysicianCareCenterId implements Serializable {

    private String physicianId;
    private Long careCenterId;

    public PhysicianCareCenterId() {}

    public PhysicianCareCenterId(String physicianId, Long careCenterId) {
        this.physicianId = physicianId;
        this.careCenterId = careCenterId;
    }

    // Getters and Setters
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

    @Override
    public int hashCode() {
        return Objects.hash(physicianId, careCenterId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PhysicianCareCenterId that = (PhysicianCareCenterId) obj;
        return Objects.equals(physicianId, that.physicianId) &&
                Objects.equals(careCenterId, that.careCenterId);
    }

}
