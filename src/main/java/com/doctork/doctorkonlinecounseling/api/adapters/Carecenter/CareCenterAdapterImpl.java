package com.doctork.doctorkonlinecounseling.api.adapters.Carecenter;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.careCenter.RequestedCareCenterInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.RequestedCareCenterOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.*;
import com.doctork.doctorkonlinecounseling.api.mappers.CareCenterMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.CareCenterService;
import com.doctork.doctorkonlinecounseling.boundary.in.Physician.PhysicianService;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.careCenter.ProvinceCitiesOutputDto;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.CareCenter.RequestedCareCenter;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.Enums.WeekDay;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Component
public class CareCenterAdapterImpl implements CareCenterAdapter {
    private final PhysicianService physicianService;
    private final CareCenterService careCenterService;

    private final CareCenterMapper careCenterMapper;

    public CareCenterAdapterImpl(PhysicianService physicianService,CareCenterService careCenterService,CareCenterMapper careCenterMapper) {
        this.physicianService = physicianService;
        this.careCenterService = careCenterService;
        this.careCenterMapper = careCenterMapper;
    }

    @Override
    public void requestToAddCareCenter(UUID userId, RequestedCareCenterInputDto requestedCareCenterInputDto) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        RequestedCareCenter requestedCareCenter = careCenterMapper.InputDtoToMongoModel(requestedCareCenterInputDto);
        requestedCareCenter.setPid(physician.getNationalCode());
        requestedCareCenter.setCreatedAt(LocalDateTime.now());
        requestedCareCenter.setState(State.Waiting);
        requestedCareCenter.setUpdatedAt(LocalDateTime.now());
        careCenterService.requestToAddCareCenter(requestedCareCenter);
    }

    @Override
    public List<RequestedCareCenterOutputDto> waitingCareCenters() {
        List<RequestedCareCenter> requestedCareCenters = careCenterService.waitingCareCenters();
        return careCenterMapper.INSTANCE.mongoModelToDto(requestedCareCenters);
    }

    @Override
    public List<CareCenterOutputDto> allActiveCareCenter() {
        return careCenterMapper.carCenterModelToDto(careCenterService.allActiveCareCenter());
    }

    @Override
    public List<ProvinceCitiesOutputDto> GetProvincesOrCities(Integer provinceId) {
        return careCenterMapper.provinceCitiesModelToDto(careCenterService.GetProvincesOrCities(provinceId));
    }

    @Override
    public List<CareCenterTypesOutputDto> allActiveCenterTypes() {
        return careCenterMapper.careCenterTypesModelToDto(careCenterService.allActiveCenterTypes());
    }

    @Override
    public RequestedCareCenterOutputDto careCenterAcceptanceDecision(RequestedCareCenterOutputDto requestedCareCenterOutputDto , Long careCenterTypeId) {
        RequestedCareCenter requestedCareCenter = careCenterMapper.INSTANCE.mongoDtoToModel(requestedCareCenterOutputDto);
        requestedCareCenter.setUpdatedAt(LocalDateTime.now());
        return careCenterMapper.INSTANCE.mongoModelToDto(careCenterService.CareCenterAcceptanceDecision(requestedCareCenter , careCenterTypeId));
    }

    @Override
    public PhysicianCareCenterOutputDto addPhysicianToCareCenter(UUID userId, Long careCenterId, Set<WeekDay> days) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return careCenterMapper.physicianCareCenterModelToDto(careCenterService.addPhysicianToCareCenter(physician.getNationalCode(),careCenterId,days));
    }

    @Override
    public PhysicianCareCenterOutputDto editPhysicianWorkingDaysWithCareCenter(UUID userId, Long careCenterId, Set<WeekDay> days) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return careCenterMapper.physicianCareCenterModelToDto(careCenterService.editPhysicianWorkingDaysWithCareCenter(physician.getNationalCode(),careCenterId,days));
    }

    @Override
    public void terminationOfCooperationPhysicianWithCareCenter(UUID userId, Long careCenterId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        careCenterService.TerminationOfCooperationPhysicianWithCareCenter(physician.getNationalCode(),careCenterId);

    }

    @Override
    public Set<PhysicianCareCenterOutputDto> allTheCareCentersWhereThePhysicianWorks(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return careCenterMapper.physicianCareCenterModelToDto(careCenterService.AllTheCareCentersWhereThePhysicianWorks(physician.getNationalCode()));
    }

    @Override
    public CareCenterOutputDtoWithPhysicians allPhysiciansOfCareCenter(Long centerId) {
        return careCenterMapper.carCenterWithPhysiciansModelToDto(careCenterService.allPhysiciansOfCareCenter(centerId));
    }

    @Override
    public CareCenterTypeOutputWithCareCenters allCareCentersOfCareCenterType(Long careCenterTypeId) {
        return careCenterMapper.careCenterTypeWithCareCentersModelToDto(careCenterService.allCareCentersOfCareCenterType(careCenterTypeId));
    }
}
