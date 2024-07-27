package com.doctork.doctorkonlinecounseling.domain.CareCenter;

import jakarta.persistence.Column;

public class ProvinceCities {

    private Long id;

    private Integer parent;
    private String title;

    public ProvinceCities(Long id, Integer parent, String title) {
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
