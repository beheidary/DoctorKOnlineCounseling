package com.doctork.doctorkonlinecounseling.boundary.exit.Counseling;

import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;

public interface OnlineCounselingRepository {


    OnlineCounseling createCounseling (String patientId, String physicianId, Long priceId);

    OnlineCounseling findCounseling (Long counselingId);






}
