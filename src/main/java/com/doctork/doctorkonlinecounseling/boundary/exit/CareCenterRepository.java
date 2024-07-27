package com.doctork.doctorkonlinecounseling.boundary.exit;

import com.doctork.doctorkonlinecounseling.domain.CareCenter.*;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;

import java.util.List;
import java.util.Set;

public interface CareCenterRepository {


    void requestToAddCareCenter(RequestedCareCenter requestedCareCenter);
    List<RequestedCareCenter> waitingCareCenters ();

    PhysicianCareCenter CareCenterAcceptanceDecision(RequestedCareCenter requestedCareCenter , Long careCenterTypeId);

    PhysicianCareCenter addPhysicianToCareCenter(String physicianId, Long careCenterId , Set<WeekDay> days);
    PhysicianCareCenter editPhysicianWorkingDaysWithCareCenter(String physicianId, Long careCenterId , Set<WeekDay> days);

    void TerminationOfCooperationPhysicianWithCareCenter (String physicianId, Long careCenterId);

    Set<PhysicianCareCenter> AllTheCareCentersWhereThePhysicianWorks (String physicianId);

    List<CareCenterTypes> allActiveCenterTypes ();
    List<CareCenter> allActiveCareCenter ();

    CareCenterWithPhysicians allPhysiciansOfCareCenter (Long centerId);

    CareCenterTypesWithCareCenters allCareCentersOfCareCenterType (Long careCenterTypeId);

    List<ProvinceCities> GetProvincesOrCities (Integer provinceId);


}
