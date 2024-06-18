package com.doctork.doctorkonlinecounseling.api.adapters.Counseling;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.Counseling.OnlineCounselingOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.CounselingMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Counseling.OnlineCounselingService;
import com.doctork.doctorkonlinecounseling.domain.Counseling.OnlineCounseling;
import org.springframework.stereotype.Component;

@Component
public class OnlineCounselingAdapterImpl implements OnlineCounselingAdapter {

    private final OnlineCounselingService onlineCounselingService;
    private final CounselingMapper counselingMapper;

    public OnlineCounselingAdapterImpl(OnlineCounselingService onlineCounselingService, CounselingMapper counselingMapper) {
        this.onlineCounselingService = onlineCounselingService;
        this.counselingMapper = counselingMapper;
    }

    @Override
    public OnlineCounselingOutputDto createCounseling(Long patientId, Long physicianId, Long priceId) {

        OnlineCounseling onlineCounseling = onlineCounselingService.createCounseling(patientId,physicianId,priceId);
        OnlineCounselingOutputDto onlineCounselingOutputDto = counselingMapper.onlineCounselingModelToOutputDto(onlineCounseling);
        onlineCounselingOutputDto.setPatientId(onlineCounseling.getPatient().getNationalCode());
        onlineCounselingOutputDto.setPriceId(onlineCounseling.getPrice().getId());
        onlineCounselingOutputDto.setPhysicianId(onlineCounseling.getPhysician().getNationalCode());
        onlineCounselingOutputDto.setCreatorUserId(onlineCounseling.getCreatorUser().getId());

        return onlineCounselingOutputDto;
    }

    @Override
    public OnlineCounselingOutputDto patientApproveCounseling(Long counselingId, String paymentExpirationId) {
        OnlineCounseling onlineCounseling = onlineCounselingService.patientApproveCounseling(counselingId,paymentExpirationId);
        OnlineCounselingOutputDto onlineCounselingOutputDto = counselingMapper.onlineCounselingModelToOutputDto(onlineCounseling);


        onlineCounselingOutputDto.setPatientId(onlineCounseling.getPatient().getNationalCode());
        onlineCounselingOutputDto.setPriceId(onlineCounseling.getPrice().getId());
        onlineCounselingOutputDto.setPhysicianId(onlineCounseling.getPhysician().getNationalCode());
        onlineCounselingOutputDto.setCreatorUserId(onlineCounseling.getCreatorUser().getId());

        return onlineCounselingOutputDto;
    }

    @Override
    public OnlineCounselingOutputDto findCounseling(Long counselingId) {
        OnlineCounseling onlineCounseling = onlineCounselingService.findCounseling(counselingId);
        OnlineCounselingOutputDto onlineCounselingOutputDto = counselingMapper.onlineCounselingModelToOutputDto(onlineCounseling);
        onlineCounselingOutputDto.setPatientId(onlineCounseling.getPatient().getNationalCode());
        onlineCounselingOutputDto.setPriceId(onlineCounseling.getPrice().getId());
        onlineCounselingOutputDto.setPhysicianId(onlineCounseling.getPhysician().getNationalCode());
        onlineCounselingOutputDto.setCreatorUserId(onlineCounseling.getCreatorUser().getId());

        return onlineCounselingOutputDto;
    }
}
