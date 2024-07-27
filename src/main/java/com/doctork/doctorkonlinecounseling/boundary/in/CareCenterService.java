package com.doctork.doctorkonlinecounseling.boundary.in;

import com.doctork.doctorkonlinecounseling.domain.CareCenter.*;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;

import java.util.List;
import java.util.Set;

public interface CareCenterService {


    void requestToAddCareCenter(RequestedCareCenter requestedCareCenter);

    List<CareCenterTypes> allActiveCenterTypes ();
    List<RequestedCareCenter> waitingCareCenters ();

    List<CareCenter> allActiveCareCenter ();

    List<ProvinceCities> GetProvincesOrCities (Integer provinceId);


    RequestedCareCenter CareCenterAcceptanceDecision(RequestedCareCenter requestedCareCenter , Long careCenterTypeId);
    PhysicianCareCenter addPhysicianToCareCenter(String physicianId, Long careCenterId , Set<WeekDay> days);

    PhysicianCareCenter editPhysicianWorkingDaysWithCareCenter(String physicianId, Long careCenterId , Set<WeekDay> days);

    void TerminationOfCooperationPhysicianWithCareCenter (String physicianId, Long careCenterId);

    Set<PhysicianCareCenter> AllTheCareCentersWhereThePhysicianWorks (String physicianId);

    CareCenterWithPhysicians allPhysiciansOfCareCenter (Long centerId);

    CareCenterTypesWithCareCenters allCareCentersOfCareCenterType (Long careCenterTypeId);





}
