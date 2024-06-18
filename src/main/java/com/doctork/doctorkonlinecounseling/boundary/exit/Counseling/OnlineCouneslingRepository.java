package com.doctork.doctorkonlinecounseling.boundary.exit.Counseling;

import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;

public interface OnlineCouneslingRepository {


    OnlineCounseling createCounseling (Long patientId, Long physicianId, Long priceId);






}
