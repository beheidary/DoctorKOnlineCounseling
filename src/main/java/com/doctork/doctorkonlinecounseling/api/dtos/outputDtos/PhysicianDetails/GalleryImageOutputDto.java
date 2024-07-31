package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails;

public class GalleryImageOutputDto {


    private Long id;
    private String imageName;


    public GalleryImageOutputDto(Long id, String imageName) {
        this.id = id;
        this.imageName = imageName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getImageName() {
        return imageName;
    }

    public void setImageName(String imageName) {
        this.imageName = imageName;
    }
}
