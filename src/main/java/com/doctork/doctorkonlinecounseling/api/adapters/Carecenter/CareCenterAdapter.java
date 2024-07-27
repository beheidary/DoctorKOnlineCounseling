package com.doctork.doctorkonlinecounseling.api.adapters.Carecenter;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.careCenter.RequestedCareCenterInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.RequestedCareCenterOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.*;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.ProvinceCitiesOutputDto;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;


import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface CareCenterAdapter {

    void requestToAddCareCenter (UUID userId , RequestedCareCenterInputDto requestedCareCenterInputDto);

    List<RequestedCareCenterOutputDto> waitingCareCenters ();

    List<CareCenterOutputDto> allActiveCareCenter ();

    List<ProvinceCitiesOutputDto> GetProvincesOrCities(Integer provinceId);

    List<CareCenterTypesOutputDto> allActiveCenterTypes ();

    RequestedCareCenterOutputDto careCenterAcceptanceDecision(RequestedCareCenterOutputDto requestedCareCenterOutputDto , Long careCenterTypeId );


    PhysicianCareCenterOutputDto addPhysicianToCareCenter(UUID userId, Long careCenterId , Set<WeekDay> days);

    PhysicianCareCenterOutputDto editPhysicianWorkingDaysWithCareCenter(UUID userId, Long careCenterId , Set<WeekDay> days);

    void terminationOfCooperationPhysicianWithCareCenter(UUID userId, Long careCenterId);

    Set<PhysicianCareCenterOutputDto> allTheCareCentersWhereThePhysicianWorks(UUID userId);

    CareCenterOutputDtoWithPhysicians allPhysiciansOfCareCenter (Long centerId);

    CareCenterTypeOutputWithCareCenters allCareCentersOfCareCenterType (Long careCenterTypeId);



}
