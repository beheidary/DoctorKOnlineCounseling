package com.doctork.doctorkonlinecounseling.domain.RabbitMqMessages;

import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;

import java.io.Serializable;

public class PhysicianStatusChangeMessage implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long nationalCode;
    private PhysicianStatus status;
    private long timestamp;

    // Getters and setters
    public Long getNationalCode() {
        return nationalCode;
    }

    public void setNationalCode(Long nationalCode) {
        this.nationalCode = nationalCode;
    }

    public PhysicianStatus getStatus() {
        return status;
    }

    public void setStatus(PhysicianStatus status) {
        this.status = status;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
