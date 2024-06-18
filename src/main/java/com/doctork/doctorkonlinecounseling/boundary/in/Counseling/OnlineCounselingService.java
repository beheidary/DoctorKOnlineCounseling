package com.doctork.doctorkonlinecounseling.boundary.in.Counseling;

import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;

public interface OnlineCounselingService {


    OnlineCounseling createCounseling (Long patientId, Long physicianId, Long priceId);
}
