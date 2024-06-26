package com.doctork.doctorkonlinecounseling.domain.Price;

import com.doctork.doctorkonlinecounseling.domain.Enums.PriceStatus;

import java.time.LocalDateTime;

public class Services {

    private Long id;

    private String description;
    private String title;

    private LocalDateTime saveDateTime;

    private LocalDateTime updateDateTime;
    private PriceStatus priceStatus;


    public Services(Long id, String description, String title, LocalDateTime saveDateTime, LocalDateTime updateDateTime, PriceStatus priceStatus) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.priceStatus = priceStatus;
    }

    public Services() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalDateTime getSaveDateTime() {
        return saveDateTime;
    }

    public void setSaveDateTime(LocalDateTime saveDateTime) {
        this.saveDateTime = saveDateTime;
    }

    public LocalDateTime getUpdateDateTime() {
        return updateDateTime;
    }

    public void setUpdateDateTime(LocalDateTime updateDateTime) {
        this.updateDateTime = updateDateTime;
    }

    public PriceStatus getStatus() {
        return priceStatus;
    }

    public void setStatus(PriceStatus priceStatus) {
        this.priceStatus = priceStatus;
    }


}
