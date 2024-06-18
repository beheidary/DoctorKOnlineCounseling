package com.doctork.doctorkonlinecounseling.database.entities.Counseling;

import com.doctork.doctorkonlinecounseling.database.entities.Patient.PatientEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import com.doctork.doctorkonlinecounseling.database.entities.Price.PriceEntity;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.CounselingState;
import jakarta.persistence.*;
import org.simpleframework.xml.Default;

import java.time.LocalDateTime;
@Entity
@Table(name = "onlineCounselings")
public class OnlineCounselingEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity CreatorUser;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_id")
    private PriceEntity price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physician_id")
    private PhysicianEntity physician;

    @Column(nullable = false)
    private CounselingState counselingState;
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public OnlineCounselingEntity() {
    }

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }


    public OnlineCounselingEntity(Long id, UserEntity creatorUser, PriceEntity price, PatientEntity patient, PhysicianEntity physician, CounselingState counselingState, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.id = id;
        CreatorUser = creatorUser;
        this.price = price;
        this.patient = patient;
        this.physician = physician;
        this.counselingState = counselingState;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public OnlineCounselingEntity(UserEntity creatorUser, PatientEntity patient, PhysicianEntity physician,PriceEntity price, CounselingState counselingState) {
        this(null,creatorUser,price,patient,physician,counselingState,LocalDateTime.now(),LocalDateTime.now());
    }


        public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserEntity getCreatorUser() {
        return CreatorUser;
    }

    public void setCreatorUser(UserEntity creatorUser) {
        CreatorUser = creatorUser;
    }

    public PriceEntity getPrice() {
        return price;
    }

    public void setPrice(PriceEntity price) {
        this.price = price;
    }

    public PatientEntity getPatient() {
        return patient;
    }

    public void setPatient(PatientEntity patient) {
        this.patient = patient;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
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
}
