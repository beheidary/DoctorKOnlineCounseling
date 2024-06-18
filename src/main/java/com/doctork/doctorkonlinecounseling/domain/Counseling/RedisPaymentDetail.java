package com.doctork.doctorkonlinecounseling.domain.Counseling;

public class RedisPaymentDetail {


    private Double UserWalletBalance;
    private Double CounselingPrice;
    private Double TotalPayablePrice;


    public RedisPaymentDetail(Double counselingPrice,Double userWalletBalance, Double totalPayablePrice) {
        UserWalletBalance = userWalletBalance;
        CounselingPrice = counselingPrice;
        TotalPayablePrice = totalPayablePrice;
    }

    public RedisPaymentDetail() {
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
