package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.ExpertiseMapper;
import com.doctork.doctorkonlinecounseling.api.mappers.PhysicianMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ExpertiseAdapterImpl implements ExpertiseAdapter {

    ExpertiseService expertiseService;
    ExpertiseMapper expertiseMapper;

    private final PhysicianMapper physicianMapper;


    public ExpertiseAdapterImpl(PhysicianMapper physicianMapper, ExpertiseService expertiseService,ExpertiseMapper expertiseMapper) {
        this.expertiseService = expertiseService;
        this.physicianMapper = physicianMapper;
        this.expertiseMapper = expertiseMapper;
    }


    @Override
    public List<ExpertiseOutputDto> getExpertises() {

        List<Expertise> expertises = expertiseService.getExpertises();

        return expertises.stream().map(expertiseMapper::modelToOutput).collect(Collectors.toList());

    }

    @Override
    public ExpertiseOutputDto getExpertise(ExpertiseLatinNames lotinName) {

        return expertiseMapper.modelToOutput(expertiseService.getExpertise(lotinName));

    }

    @Override
    public ExpertiseOutputDto addExpertise(ExpertiseInputDto expertiseInputDto) {
        Expertise expertise = expertiseMapper.inputToModelWithoutDoctor(expertiseInputDto);
        expertise = expertiseService.addExpertise(expertise);
        return expertiseMapper.modelToOutput(expertise);
    }

    @Override
    public ExpertiseOutputDto editExpertise(Long expertiseId, ExpertiseInputDto expertiseInputDto) {
        Expertise expertise = expertiseMapper.inputToModelWithoutDoctor(expertiseInputDto);
        expertise = expertiseService.editExpertise(expertiseId, expertise);
        return expertiseMapper.modelToOutput(expertise);
    }

    @Override
    public ExpertiseOutputDto addPhysicianExpertise(String nationalCode, ExpertiseInputDto expertiseInputDto) {
        Expertise expertise = expertiseMapper.inputToModelWithoutDoctor(expertiseInputDto);
        expertise = expertiseService.addPhysicianExpertise( nationalCode, expertise);
        return expertiseMapper.modelToOutput(expertise);
    }

    @Override
    public List<TopExpertisesDto> findBestExpertisePhysicians() {

        List<TopExpertisesDto> expertise = new ArrayList<>();
        for(TopExpertises topExpertises : expertiseService.findBestExpertisePhysicians()){
            List<PhysicianOutputDto> physicians = new ArrayList<>();
            for (Physician physician : topExpertises.getPhysicians()){
                physicians.add(physicianMapper.modelToOutput(physician));
            }
            TopExpertisesDto topExpertisesDto = expertiseMapper.topModelToOutput(topExpertises);
            topExpertisesDto.setPhysicians(physicians);
            expertise.add(topExpertisesDto);
        }

        return expertise;
    }

}
