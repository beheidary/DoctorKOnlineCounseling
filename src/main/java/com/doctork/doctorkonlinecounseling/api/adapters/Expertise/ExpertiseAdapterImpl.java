package com.doctork.doctorkonlinecounseling.api.adapters.Expertise;

import com.doctork.doctorkonlinecounseling.api.dtos.RequestedExpertiseDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.Physician.ExpertiseInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.ExpertiseOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.physician.PhysicianOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.ExpertiseMapper;
import com.doctork.doctorkonlinecounseling.api.mappers.PhysicianMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Physician.PhysicianService;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Expertise.RequestedExpertise;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class ExpertiseAdapterImpl implements ExpertiseAdapter {

    private final ExpertiseService expertiseService;
    private final ExpertiseMapper expertiseMapper;

    private final PhysicianService physicianService;

    private final PhysicianMapper physicianMapper;


    public ExpertiseAdapterImpl(PhysicianMapper physicianMapper, ExpertiseService expertiseService,ExpertiseMapper expertiseMapper,PhysicianService physicianService) {
        this.expertiseService = expertiseService;
        this.physicianService = physicianService;
        this.physicianMapper = physicianMapper;
        this.expertiseMapper = expertiseMapper;
    }


    @Override
    public List<ExpertiseOutputDto> getExpertises() {

        List<Expertise> expertises = expertiseService.getExpertises();

        return expertises.stream().map(expertiseMapper::modelToOutput).collect(Collectors.toList());

    }

    @Override
    public ExpertiseOutputDto getExpertise(String latinName) {

        return expertiseMapper.modelToOutput(expertiseService.getExpertise(latinName));

    }

    @Override
    public ExpertiseOutputDto editExpertise(Long expertiseId, ExpertiseInputDto expertiseInputDto) {
        Expertise expertise = expertiseMapper.inputToModelWithoutDoctor(expertiseInputDto);
        expertise = expertiseService.editExpertise(expertiseId, expertise);
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

    @Override
    public Set<ExpertiseOutputDto> uploadExpertise(UUID userId, Set<ExpertiseInputDto> expertise) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return  expertiseMapper.modelToOutputDto(expertiseService.uploadExpertise(physician.getNationalCode(),expertiseMapper.inputDtoToModel(expertise)));

    }

    @Override
    public Set<ExpertiseOutputDto> allPhysicianExpertises(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return  expertiseMapper.modelToOutputDto(expertiseService.allPhysicianExpertises(physician.getNationalCode()));
    }

    @Override
    public void requestToAddExpertise(UUID userId, String name) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        RequestedExpertise requestedExpertise = new RequestedExpertise(physician.getNationalCode() ,name);
        expertiseService.requestToAddExpertise(requestedExpertise);

    }

    @Override
    public List<RequestedExpertiseDto> waitingExpertises() {
        List<RequestedExpertise> requestedExpertises = expertiseService.waitingExpertises();
        return expertiseMapper.INSTANCE.mongoModelToDto(requestedExpertises);
    }

    @Override
    public RequestedExpertiseDto expertiseChangeState(RequestedExpertiseDto requestedExpertiseDto) {
        RequestedExpertise requestedExpertise = expertiseMapper.INSTANCE.mongoDtoToModel(requestedExpertiseDto);
        requestedExpertise.setUpdatedAt(LocalDateTime.now());
        return expertiseMapper.INSTANCE.mongoModelToDto(expertiseService.expertiseChangeState(requestedExpertise));
    }

}
