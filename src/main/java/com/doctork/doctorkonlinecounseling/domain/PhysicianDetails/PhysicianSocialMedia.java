package com.doctork.doctorkonlinecounseling.domain.PhysicianDetails;

import java.util.Objects;

public class PhysicianSocialMedia {

    private SocialMedia socialMedia;

    private String link;

    public SocialMedia getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMedia socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    // equals and hashCode implementations


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PhysicianSocialMedia that = (PhysicianSocialMedia) o;

        if (!socialMedia.equals(that.socialMedia)) return false;
        return link.equals(that.link);
    }

    @Override
    public int hashCode() {
        int result = socialMedia.hashCode();
        result = 31 * result + link.hashCode();
        return result;
    }
}
