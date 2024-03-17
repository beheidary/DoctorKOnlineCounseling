package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.api.mappers.Expertise.ExpertiseMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpertiseAdapterImpl implements ExpertiseAdapter {

    ExpertiseService expertiseService;
    ExpertiseMapper expertiseMapper;


    public ExpertiseAdapterImpl(ExpertiseService expertiseService,ExpertiseMapper expertiseMapper) {
        this.expertiseService = expertiseService;
        this.expertiseMapper = expertiseMapper;
    }


    @Override
    public ExpertiseOutputDTO addDoctorExpertise(String PSCode, ExpertiseInputDTO expertiseInputDTO) {

        Expertise expertise = expertiseService.addDoctorExpertise(PSCode,expertiseMapper.inputToModel(expertiseInputDTO));

        return expertiseMapper.modelToOutput(expertise);




    }

    @Override
    public List<ExpertiseOutputDTO> getExpertises() {

        List<Expertise> expertises = expertiseService.getExpertises();

        return expertises.stream().map(expertiseMapper::modelToOutput).collect(Collectors.toList());

    }
}
