package com.doctork.doctorkonlinecounseling.UseCase.carecenter;

import com.doctork.doctorkonlinecounseling.boundary.exit.CareCenterRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.CareCenterService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.CareCenter.*;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CareCenterServiceImpl implements CareCenterService {

    private final CareCenterRepository careCenterRepository;
    public CareCenterServiceImpl(CareCenterRepository careCenterRepository) {
        this.careCenterRepository = careCenterRepository;
    }

    @Override
    public void requestToAddCareCenter(RequestedCareCenter requestedCareCenter) {
        careCenterRepository.requestToAddCareCenter(requestedCareCenter);

    }

    @Override
    public List<CareCenterTypes> allActiveCenterTypes() {
        return careCenterRepository.allActiveCenterTypes();
    }

    @Override
    public List<RequestedCareCenter> waitingCareCenters() {
        return careCenterRepository.waitingCareCenters();
    }

    @Override
    public List<CareCenter> allActiveCareCenter() {
        return careCenterRepository.allActiveCareCenter();
    }

    @Override
    public List<ProvinceCities> GetProvincesOrCities(Integer provinceId) {
        if(provinceId == null || provinceId == null)
            throw new IdInputException();
        return careCenterRepository.GetProvincesOrCities(provinceId);
    }

    @Override
    public RequestedCareCenter CareCenterAcceptanceDecision(RequestedCareCenter requestedCareCenter , Long careCenterTypeId) {
        // when careCenter rejected by support careCenterTypeId must be -1 or null
        PhysicianCareCenter physicianCareCenter = careCenterRepository.CareCenterAcceptanceDecision(requestedCareCenter , careCenterTypeId);
        if (requestedCareCenter.getState() == State.Approved)
            careCenterRepository.addPhysicianToCareCenter(physicianCareCenter.getPhysicianId() , physicianCareCenter.getCareCenter().getId(),physicianCareCenter.getDays());
        return requestedCareCenter;
    }

    @Override
    public PhysicianCareCenter addPhysicianToCareCenter(String physicianId, Long careCenterId, Set<WeekDay> days) {
        if(physicianId == null || careCenterId == null)
            throw new IdInputException();
        return careCenterRepository.addPhysicianToCareCenter(physicianId , careCenterId,days);
    }

    @Override
    public PhysicianCareCenter editPhysicianWorkingDaysWithCareCenter(String physicianId, Long careCenterId, Set<WeekDay> days) {
        if(physicianId == null || careCenterId == null)
            throw new IdInputException();
        return careCenterRepository.editPhysicianWorkingDaysWithCareCenter(physicianId,careCenterId,days);
    }

    @Override
    public void TerminationOfCooperationPhysicianWithCareCenter(String physicianId, Long careCenterId) {
        if(physicianId == null || careCenterId == null)
            throw new IdInputException();
        careCenterRepository.TerminationOfCooperationPhysicianWithCareCenter(physicianId,careCenterId);
    }

    @Override
    public Set<PhysicianCareCenter> AllTheCareCentersWhereThePhysicianWorks(String physicianId) {
        if(physicianId == null)
            throw new IdInputException();
        return careCenterRepository.AllTheCareCentersWhereThePhysicianWorks(physicianId);
    }

    @Override
    public CareCenterWithPhysicians allPhysiciansOfCareCenter(Long centerId) {
        if(centerId == null)
            throw new IdInputException();
        return careCenterRepository.allPhysiciansOfCareCenter(centerId);
    }

    @Override
    public CareCenterTypesWithCareCenters allCareCentersOfCareCenterType(Long careCenterTypeId) {
        if(careCenterTypeId == null)
            throw new IdInputException();
        CareCenterTypesWithCareCenters careCenterTypesWithCareCenters = careCenterRepository.allCareCentersOfCareCenterType(careCenterTypeId);
        if (careCenterTypesWithCareCenters == null) {
            throw new IllegalStateException("No care centers found for the given type ID.");
        }

        List<CareCenterWithPhysicians> updatedCareCenters = careCenterTypesWithCareCenters.getCareCenters().stream()
                .map(careCenter -> Optional.ofNullable(careCenterRepository.allPhysiciansOfCareCenter(careCenter.getId()))
                        .orElse(careCenter))
                .collect(Collectors.toList());

        careCenterTypesWithCareCenters.setCareCenters(updatedCareCenters);
        return careCenterTypesWithCareCenters;
    }

}
