package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails;


import jakarta.validation.constraints.NotNull;

public class PhysicianSocialMediaInputDto {

    @NotNull
    private SocialMediaInputDto socialMedia;
    @NotNull
    private String link;

    public PhysicianSocialMediaInputDto(SocialMediaInputDto socialMedia, String link) {
        this.socialMedia = socialMedia;
        this.link = link;
    }

    public SocialMediaInputDto getSocialMedia() {
        return socialMedia;
    }

    public void setSocialMedia(SocialMediaInputDto socialMedia) {
        this.socialMedia = socialMedia;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
