package com.doctork.doctorkonlinecounseling.api.adapters.Counseling;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.Counseling.OnlineCounselingOutputDto;

public interface OnlineCounselingAdapter {


    OnlineCounselingOutputDto createCounseling (Long patientId, Long physicianId,Long priceId);



}
