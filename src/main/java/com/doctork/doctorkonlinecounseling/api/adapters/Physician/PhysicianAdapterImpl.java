package com.doctork.doctorkonlinecounseling.api.adapters.Physician;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.PhysicianInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.PhysicianMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Physician.PhysicianService;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import com.doctork.doctorkonlinecounseling.domain.Enums.PhysicianStatus;
import org.springframework.stereotype.Component;

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
    public PhysicianOutputDto physicianCompleteProfile(PhysicianInputDto physicianInputDto , Long nationalCode) {

        Physician physician = physicianMapper.inputToModel(physicianInputDto);

        physician = physicianService.PhysicianCompleteProfile(physician, nationalCode);

        return physicianMapper.modelToOutput(physician);

    }

    @Override
    public PhysicianOutputDto physicianEditProfile(PhysicianInputDto physicianInputDto, Long nationalCode) {

        Physician physician = physicianMapper.inputToModel(physicianInputDto);

        physician = physicianService.PhysicianEditProfile


                (physician , nationalCode);

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
    public PhysicianOutputDto fetchPhysician(Long nationalCode) {


        return physicianMapper.modelToOutput(physicianService.fetchPhysician(nationalCode));
    }



    @Override
    public PhysicianOutputDto changeStatus(Long nationalCode, PhysicianStatus status) {

        return physicianMapper.modelToOutput(physicianService.changeStatus(nationalCode,status));


    }


}
