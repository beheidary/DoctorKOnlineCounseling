package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter;

public class ProvinceCitiesOutputDto {


    private Long id;

    private Integer parent;
    private String title;

    public ProvinceCitiesOutputDto(Long id, Integer parent, String title) {
        this.id = id;
        this.parent = parent;
        this.title = title;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getParent() {
        return parent;
    }

    public void setParent(Integer parent) {
        this.parent = parent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
