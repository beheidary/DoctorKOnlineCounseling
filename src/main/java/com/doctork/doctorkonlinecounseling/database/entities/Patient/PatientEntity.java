package com.doctork.doctorkonlinecounseling.database.entities.Patient;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.Gender;
import jakarta.persistence.*;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "patients")
public class PatientEntity {


    @Id
    private String nationalCode;

    @Column(name = "gender" , nullable = false)
    private Gender gender;

    @Column(name = "firstName",nullable = false)
    private String firstName;

    @Column(name = "lastName",nullable = false)
    private String lastName;

    @Column(name = "dateOfBirth",nullable = false)
    private LocalDate dateOfBirth;

    @UpdateTimestamp
    @Column(name = "updated_at",nullable = true)
    private LocalDateTime updated_At;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private UserEntity user;

    public PatientEntity(UserEntity user, String nationalCode, Gender gender, String firstName, String lastName, LocalDate dateOfBirth, LocalDateTime updated_At) {
        this.nationalCode = nationalCode;
        this.gender = gender;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.dateOfBirth = dateOfBirth;
        this.updated_At = updated_At;
    }

    public PatientEntity() {

    }


    public String getNationalCode() {
        return nationalCode;
    }
    public void setNationalCode(String nationalCode) {
        this.nationalCode = nationalCode;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public LocalDateTime getUpdated_At() {
        return updated_At;
    }

    public void setUpdated_At(LocalDateTime updated_At) {
        this.updated_At = updated_At;
    }
}
