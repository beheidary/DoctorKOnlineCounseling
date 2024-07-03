package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.Counseling;

import com.doctork.doctorkonlinecounseling.domain.Enums.CounselingState;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.doctork.doctorkonlinecounseling.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class OnlineCounselingOutputDto {


    private Long id;

    private UUID CreatorUserId;

    private Long priceId;

    private String patientId;

    private String physicianId;

    private CounselingState counselingState;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    private Double UserWalletBalance;
    private Double CounselingPrice;
    private Double TotalPayablePrice;

    private String paymentExpirationId;


    public OnlineCounselingOutputDto(Long id, UUID creatorUserId, Long priceId, String patientId, String physicianId, CounselingState counselingState, LocalDateTime createdAt, LocalDateTime updatedAt, Double userWalletBalance, Double counselingPrice, Double totalPayablePrice, String paymentExpirationId) {
        this.id = id;
        CreatorUserId = creatorUserId;
        this.priceId = priceId;
        this.patientId = patientId;
        this.physicianId = physicianId;
        this.counselingState = counselingState;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        UserWalletBalance = userWalletBalance;
        CounselingPrice = counselingPrice;
        TotalPayablePrice = totalPayablePrice;
        this.paymentExpirationId = paymentExpirationId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getCreatorUserId() {
        return CreatorUserId;
    }

    public void setCreatorUserId(UUID creatorUserId) {
        CreatorUserId = creatorUserId;
    }

    public Long getPriceId() {
        return priceId;
    }

    public void setPriceId(Long priceId) {
        this.priceId = priceId;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPhysicianId() {
        return physicianId;
    }

    public void setPhysicianId(String physicianId) {
        this.physicianId = physicianId;
    }

    public CounselingState getCounselingState() {
        return counselingState;
    }

    public void setCounselingState(CounselingState counselingState) {
        this.counselingState = counselingState;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getPaymentExpirationId() {
        return paymentExpirationId;
    }

    public void setPaymentExpirationId(String paymentExpirationId) {
        this.paymentExpirationId = paymentExpirationId;
    }

    public Double getUserWalletBalance() {
        return UserWalletBalance;
    }

    public void setUserWalletBalance(Double userWalletBalance) {
        UserWalletBalance = userWalletBalance;
    }

    public Double getCounselingPrice() {
        return CounselingPrice;
    }

    public void setCounselingPrice(Double counselingPrice) {
        CounselingPrice = counselingPrice;
    }

    public Double getTotalPayablePrice() {
        return TotalPayablePrice;
    }

    public void setTotalPayablePrice(Double totalPayablePrice) {
        TotalPayablePrice = totalPayablePrice;
    }
}
