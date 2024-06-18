package com.doctork.doctorkonlinecounseling.domain.Counseling;


import com.doctork.doctorkonlinecounseling.domain.Enums.CounselingState;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
import com.doctork.doctorkonlinecounseling.domain.Price.Price;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.doctork.doctorkonlinecounseling.domain.user.User;


import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class OnlineCounseling {

    private Long id;

    private User CreatorUser;

    private Price price;

    private Patient patient;

    private Physician physician;

    private CounselingState counselingState;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Double UserWalletBalance;
    private Double CounselingPrice;
    private Double TotalPayablePrice;

    private String paymentExpirationId;


    public OnlineCounseling(){

    }

    public OnlineCounseling(Long id, User creatorUser, Price price, Patient patient, Physician physician, CounselingState counselingState, LocalDateTime createdAt, LocalDateTime updatedAt, Double userWalletBalance, Double counselingPrice, Double totalPayablePrice, String paymentExpirationId) {
        this.id = id;
        CreatorUser = creatorUser;
        this.price = price;
        this.patient = patient;
        this.physician = physician;
        this.counselingState = counselingState;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        UserWalletBalance = userWalletBalance;
        CounselingPrice = counselingPrice;
        TotalPayablePrice = totalPayablePrice;
        this.paymentExpirationId = paymentExpirationId;
    }

    public OnlineCounseling(User creatorUser, Price price, Patient patient, Physician physician, CounselingState counselingState, Double userWalletBalance, Double counselingPrice, Double totalPayablePrice, String paymentExpirationId, LocalDateTime createdAt, LocalDateTime updatedAt) {

        this(null, creatorUser,  price,  patient,  physician,  counselingState,  createdAt,  updatedAt,  userWalletBalance,  counselingPrice,  totalPayablePrice,  paymentExpirationId);

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getCreatorUser() {
        return CreatorUser;
    }

    public void setCreatorUser(User creatorUser) {
        CreatorUser = creatorUser;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Physician getPhysician() {
        return physician;
    }

    public void setPhysician(Physician physician) {
        this.physician = physician;
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
