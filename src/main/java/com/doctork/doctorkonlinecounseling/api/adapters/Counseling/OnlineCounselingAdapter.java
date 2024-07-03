package com.doctork.doctorkonlinecounseling.api.adapters.Counseling;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.Counseling.OnlineCounselingOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;

public interface OnlineCounselingAdapter {


    OnlineCounselingOutputDto createCounseling (String patientId, String physicianId,Long priceId);

    OnlineCounselingOutputDto patientApproveCounseling (Long counselingId, String paymentExpirationId);
    OnlineCounselingOutputDto findCounseling (Long counselingId);




}
