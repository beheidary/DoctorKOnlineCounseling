package com.doctork.doctorkonlinecounseling.database.entities.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.database.entities.Physician.PhysicianEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
@Entity
@Table(name = "physicianBankInfo")
public class PhysicianBankInfoEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "physician_id")
    private PhysicianEntity physician;

    @Column(name = "bankAccountNumber")
    private String bankAccountNumber;
    @Column(name = "bankCardNumber")
    @Size(min = 16, max = 16, message = "The bank card number must be exactly 16 characters long.")
    private String bankCardNumber;
    @Column(name = "bankShebaNumber" , updatable = false ,nullable = false)
    @Size(min = 24, max = 24, message = "The bank Sheba number must be exactly 24 characters long.")
    private String bankShebaNumber;

    @CreationTimestamp
    @Column(name = "saveDateTime", nullable = false)
    private LocalDateTime saveDateTime;
    @UpdateTimestamp
    @Column(name = "updateDateTime", nullable = true)
    private LocalDateTime updateDateTime;

    public PhysicianBankInfoEntity() {
    }

    public PhysicianBankInfoEntity(Long id, PhysicianEntity physician, String bankAccountNumber, String bankCardNumber, String bankShebaNumber, LocalDateTime saveDateTime, LocalDateTime updateDateTime) {
        this.id = id;
        this.physician = physician;
        this.bankAccountNumber = bankAccountNumber;
        this.bankCardNumber = bankCardNumber;
        this.bankShebaNumber = bankShebaNumber;
        this.saveDateTime = saveDateTime;
        this.updateDateTime = updateDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PhysicianEntity getPhysician() {
        return physician;
    }

    public void setPhysician(PhysicianEntity physician) {
        this.physician = physician;
    }

    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public String getBankCardNumber() {
        return bankCardNumber;
    }

    public void setBankCardNumber(String bankCardNumber) {
        this.bankCardNumber = bankCardNumber;
    }

    public String getBankShebaNumber() {
        return bankShebaNumber;
    }

    public void setBankShebaNumber(String bankShebaNumber) {
        this.bankShebaNumber = bankShebaNumber;
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
}
