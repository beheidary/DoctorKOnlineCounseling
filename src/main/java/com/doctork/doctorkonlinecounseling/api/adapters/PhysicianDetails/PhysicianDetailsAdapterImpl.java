package com.doctork.doctorkonlinecounseling.api.adapters.PhysicianDetails;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.PhysicianSocialMediaInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.PhysicianSocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDtos.PhysicianDetails.SicknessInputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SicknessOutputDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDtos.PhysicianDetails.SocialMediaOutputDto;
import com.doctork.doctorkonlinecounseling.api.mappers.PhysicianDetailsMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.Physician.PhysicianService;
import com.doctork.doctorkonlinecounseling.boundary.in.PhysicianDetails.PhysicianDetailsService;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Enums.State;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.PhysicianSocialMedia;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.Sickness;
import com.doctork.doctorkonlinecounseling.domain.PhysicianDetails.SocialMedia;
import com.doctork.doctorkonlinecounseling.domain.physician.Physician;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.UUID;

@Component
public class PhysicianDetailsAdapterImpl implements PhysicianDetailsAdapter {

    private final PhysicianDetailsService physicianDetailsService;
    private final PhysicianDetailsMapper physicianDetailsMapper;
    private final PhysicianService physicianService;

    public PhysicianDetailsAdapterImpl(PhysicianService physicianService,PhysicianDetailsService physicianDetailsService, PhysicianDetailsMapper physicianDetailsMapper) {
        this.physicianDetailsService = physicianDetailsService;
        this.physicianService = physicianService;
        this.physicianDetailsMapper = physicianDetailsMapper;
    }

    @Override
    public void addSickness(String sicknessName) {

        Sickness sickness = new Sickness(null,sicknessName, State.Waiting);

        physicianDetailsService.addSickness(sickness);
    }

    @Override
    public Set<SicknessOutputDto> uploadSickness(UUID userId, Set<SicknessInputDto> sicknessInputDtos) {

        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        Set<Sickness> sicknesses = physicianDetailsService.uploadSickness(physician.getNationalCode(),physicianDetailsMapper.sicknessDtoToModel(sicknessInputDtos));
        return physicianDetailsMapper.sicknessModelToDto(sicknesses);
    }

    @Override
    public Set<SicknessOutputDto> allSicknesses() {
        return physicianDetailsMapper.sicknessModelToDto(physicianDetailsService.allSicknesses(State.Approved));
    }

    @Override
    public Set<SicknessOutputDto> allSicknessesWithState(State state) {
        return physicianDetailsMapper.sicknessModelToDto(physicianDetailsService.allSicknesses(state));
    }

    @Override
    public Set<SicknessOutputDto> allPhysicianSicknesses(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.sicknessModelToDto(physicianDetailsService.allPhysicianSicknesses(physician.getNationalCode()));
    }

    @Override
    public void sicknessChangeState(Long sicknessId, State state) {
        physicianDetailsService.sicknessChangeState(sicknessId,state);
    }

    @Override
    public Set<PhysicianSocialMediaOutputDto> allPhysicianSocialMedias(UUID userId) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        return physicianDetailsMapper.physicianSocialMediaModelToDto(physicianDetailsService.allPhysicianSocialMedias(physician.getNationalCode()));
    }

    @Override
    public Set<SocialMediaOutputDto> allSocialMedias() {
        return physicianDetailsMapper.socialMediaModelToDto(physicianDetailsService.allSocialMedias());
    }

    @Override
    public Set<PhysicianSocialMediaOutputDto> uploadSocialMedias(UUID userId, Set<PhysicianSocialMediaInputDto> physicianSocialMediaInputDtos) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        Physician physician = physicianService.fetchPhysician(userEntity);
        Set<PhysicianSocialMedia> physicianSocialMedia = physicianDetailsService.uploadSocialMedias(physician.getNationalCode(),physicianDetailsMapper.physicianSocialMediaDtoToModel(physicianSocialMediaInputDtos));
        return physicianDetailsMapper.physicianSocialMediaModelToDto(physicianSocialMedia);
    }

    @Override
    public void addSocialMedia(String persianName, String latinName) {
        SocialMedia socialMedia = new SocialMedia(null,persianName,latinName,State.Approved);

        physicianDetailsService.addSocialMedia(socialMedia);
    }

}
