package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails;

public class PhysicianSocialMediaOutputDto {

    private SocialMediaOutputDto socialMedia;

    private String link;


    public PhysicianSocialMediaOutputDto(String link) {
        this.link = link;
    }

    public SocialMediaOutputDto getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMediaOutputDto socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
