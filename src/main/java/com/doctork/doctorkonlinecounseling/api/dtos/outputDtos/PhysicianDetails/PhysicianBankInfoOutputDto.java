package com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails;

public class PhysicianBankInfoOutputDto {


    private Long id;
    private String bankAccountNumber;
    private String bankCardNumber;
    private String bankShebaNumber;


    public PhysicianBankInfoOutputDto(Long id, String bankAccountNumber, String bankCardNumber, String bankShebaNumber) {
        this.id = id;
        this.bankAccountNumber = bankAccountNumber;
        this.bankCardNumber = bankCardNumber;
        this.bankShebaNumber = bankShebaNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
