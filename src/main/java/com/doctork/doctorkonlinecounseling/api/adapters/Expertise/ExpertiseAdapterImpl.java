package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.api.mappers.ExpertiseMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
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
    public List<ExpertiseOutputDTO> getExpertises() {

        List<Expertise> expertises = expertiseService.getExpertises();

        return expertises.stream().map(expertiseMapper::modelToOutput).collect(Collectors.toList());

    }

    @Override
    public ExpertiseOutputDTO getExpertise(ExpertiseLatinNames lotinName) {

        return expertiseMapper.modelToOutput(expertiseService.getExpertise(lotinName));

    }


}
