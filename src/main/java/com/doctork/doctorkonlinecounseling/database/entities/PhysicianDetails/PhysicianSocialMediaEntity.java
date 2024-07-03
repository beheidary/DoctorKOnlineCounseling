package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "physician_socialMedia")
@IdClass(PhysicianSocialMediaId.class)
public class PhysicianSocialMediaEntity {

    @Id
    @Column(name = "physician_id")
    private String physicianId;

    @Id
    @Column(name = "social_media_id")
    private Long socialMediaId;

    @ManyToOne
    @JoinColumn(name = "physician_id", insertable = false, updatable = false)
    @JsonBackReference
    private PhysicianEntity physician;

    @ManyToOne
    @JoinColumn(name = "social_media_id" , insertable = false, updatable = false)
    private SocialMediaEntity socialMedia;

    @Column(name = "link")
    private String link;

    public PhysicianSocialMediaEntity() {
    }

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

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public SocialMediaEntity getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMediaEntity socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public int hashCode() {
        return Objects.hash(physicianId, socialMediaId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        PhysicianSocialMediaEntity that = (PhysicianSocialMediaEntity) obj;
        if (physicianId != null && socialMediaId != null)
            return Objects.equals(physicianId, that.physicianId) &&
                    Objects.equals(socialMediaId, that.socialMediaId);
        else return super.equals(obj);
    }
}


