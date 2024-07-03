package com.doctork.doctorkonlinecounseling.boundary.in.Counseling;

import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;

public interface OnlineCounselingService {


    OnlineCounseling createCounseling (String patientId, String physicianId, Long priceId);

    OnlineCounseling patientApproveCounseling (Long counselingId, String paymentExpirationId);

    OnlineCounseling findCounseling (Long counselingId);
}
