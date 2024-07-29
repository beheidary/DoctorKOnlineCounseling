package com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails;

import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.Length;

public class PhysicianBankInfoInputDto {

    private String bankAccountNumber;
    @Length(min = 16 , max = 16)
    private String bankCardNumber;
    @NotNull
    @Length(min = 24 , max = 24)
    private String bankShebaNumber;

    public PhysicianBankInfoInputDto(String bankAccountNumber, String bankCardNumber, String bankShebaNumber) {
        this.bankAccountNumber = bankAccountNumber;
        this.bankCardNumber = bankCardNumber;
        this.bankShebaNumber = bankShebaNumber;
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
}
