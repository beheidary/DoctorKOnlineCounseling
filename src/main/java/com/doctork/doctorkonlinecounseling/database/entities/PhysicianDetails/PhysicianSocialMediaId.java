package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import java.io.Serializable;
import java.util.Objects;

public class PhysicianSocialMediaId implements Serializable {
    private String physicianId;
    private Long socialMediaId;

    public PhysicianSocialMediaId() {}

    public PhysicianSocialMediaId(String physicianId, Long socialMediaId) {
        this.physicianId = physicianId;
        this.socialMediaId = socialMediaId;
    }

    // Getters and Setters
    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    public Long getSocialMediaId() {
        return socialMediaId;
    }

    public void setSocialMediaId(Long socialMediaId) {
        this.socialMediaId = socialMediaId;
    }
    @Override
    public int hashCode() {
        return Objects.hash(physicianId, socialMediaId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PhysicianSocialMediaId that = (PhysicianSocialMediaId) obj;
        return Objects.equals(physicianId, that.physicianId) &&
                Objects.equals(socialMediaId, that.socialMediaId);
    }
}