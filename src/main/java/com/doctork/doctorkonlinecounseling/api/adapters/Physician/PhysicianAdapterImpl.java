package com.doctork.doctorkonlinecounseling.api.adapters.Physician;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.PhysicianInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.PhysicianMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Physician.PhysicianService;
import com.doctork.doctorkonlinecounseling.common.exceptions.notFound.PhysicianNotFoundException;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.*;

@Component
public class PhysicianAdapterImpl implements PhysicianAdapter {


    private final PhysicianService physicianService;
    private final PhysicianMapper physicianMapper;



    public PhysicianAdapterImpl(PhysicianMapper physicianMapper, PhysicianService physicianService) {
        this.physicianMapper = physicianMapper;
        this.physicianService = physicianService;
    }


    @Override
    public PhysicianOutputDto physicianCompleteProfile(PhysicianInputDto physicianInputDto) {

        Physician physician = physicianMapper.inputToModel(physicianInputDto);
        physician.setBusinessWeight(0.01);
        physician.setStatus(PhysicianStatus.Offline);
        physician.setState(State.Waiting);

        physician = physicianService.PhysicianCompleteProfile(physician);

        return physicianMapper.modelToOutput(physician);

    }

    @Override
    public PhysicianOutputDto physicianEditProfile(PhysicianInputDto physicianInputDto) {

        Physician physician = physicianMapper.inputToModel(physicianInputDto);
        physician.setState(State.Waiting);

        physician = physicianService.PhysicianEditProfile(physician);

        return physicianMapper.modelToOutput(physician);

    }



    @Override
    public List<PhysicianOutputDto> topPhysician() {
        List<PhysicianOutputDto> physicianOutputDtos = new ArrayList<>();
        for(Physician physician : physicianService.topPhysician()){
            physicianOutputDtos.add(physicianMapper.modelToOutput(physician));
        }
        return physicianOutputDtos;
    }

    @Override
    public PhysicianOutputDto physicianCheckProfile(UserEntity userEntity) {
        try {
            Physician physician = physicianService.fetchPhysician(userEntity);
            PhysicianOutputDto physicianOutputDto = physicianMapper.modelToOutput(physician);
            if (physicianOutputDto.getPhysicianSystemCode() == null || physicianOutputDto.getFirstName() == null ||
                    physicianOutputDto.getLastName() == null || physicianOutputDto.getNationalCode() == null)
                physicianOutputDto.setProfileNecessaryInfoInserted(Boolean.FALSE);
            return physicianOutputDto;

        }
        catch (NullPointerException ex) //get error not find physician
        {
            Physician physician = new Physician();
            PhysicianOutputDto physicianOutputDto = physicianMapper.modelToOutput(physician);
            physicianOutputDto.setProfileNecessaryInfoInserted(Boolean.FALSE);
            return physicianOutputDto;
        }

    }

    @Override
    public PhysicianOutputDto fetchPhysician(String nationalCode) {


        return physicianMapper.modelToOutput(physicianService.fetchPhysician(nationalCode));
    }
    public PhysicianOutputDto fetchPhysician(UserEntity userEntity) {


        return physicianMapper.modelToOutput(physicianService.fetchPhysician(userEntity));
    }



    @Override
    public PhysicianOutputDto changeStatus(String nationalCode, PhysicianStatus status) {

        return physicianMapper.modelToOutput(physicianService.changeStatus(nationalCode,status));


    }

    @Override
    public PhysicianOutputDto changeState(String nationalCode, State state) {

        return physicianMapper.modelToOutput(physicianService.changeState(nationalCode,state));


    }


}
