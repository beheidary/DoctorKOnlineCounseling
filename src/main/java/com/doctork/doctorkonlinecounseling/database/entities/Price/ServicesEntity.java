package com.doctork.doctorkonlinecounseling.database.entities.Price;

import com.doctork.doctorkonlinecounseling.domain.Enums.Status;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "services")
public class ServicesEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "description",nullable = false)
    private String description;
    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "timeSlot",nullable = false)
    private Integer timeSlot;

    @Column(name = "SlotCount",nullable = false)
    private Integer SlotCount;

    @CreationTimestamp
    @Column(name = "saveDateTime", nullable = false)
    private LocalDateTime saveDateTime;
    @UpdateTimestamp
    @Column(name = "updateDateTime", nullable = true)
    private LocalDateTime updateDateTime;
    @Column(name = "Status", nullable = false)
    private Status status;


    public ServicesEntity(Long id, String description, String title, Integer timeSlot, Integer slotCount, LocalDateTime saveDateTime, LocalDateTime updateDateTime, Status status) {
        this.id = id;
        this.description = description;
        this.title = title;
        this.timeSlot = timeSlot;
        SlotCount = slotCount;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
        this.status = status;
    }

    public ServicesEntity() {

    }

    public Integer getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(Integer timeSlot) {
        this.timeSlot = timeSlot;
    }

    public Integer getSlotCount() {
        return SlotCount;
    }

    public void setSlotCount(Integer slotCount) {
        SlotCount = slotCount;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
