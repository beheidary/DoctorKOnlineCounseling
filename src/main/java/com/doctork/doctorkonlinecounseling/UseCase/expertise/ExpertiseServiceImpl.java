package com.doctork.doctorkonlinecounseling.UseCase.expertise;

import com.doctork.doctorkonlinecounseling.boundary.exit.expertise.ExpertiseRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.expertise.ExpertiseService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Expertise.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.Expertise.Expertise;
import com.doctork.doctorkonlinecounseling.domain.Enums.ExpertiseLatinNames;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpertiseServiceImpl implements ExpertiseService {

    ExpertiseRepository expertiseRepository;

    public ExpertiseServiceImpl(ExpertiseRepository expertiseRepository) {
        this.expertiseRepository = expertiseRepository;
    }

    @Override
    public Expertise getExpertise(ExpertiseLatinNames latinName) {


        return expertiseRepository.getExpertise(latinName);

    }

    @Override
    public Expertise addPhysicianExpertise(String nationalCode, Expertise expertise) {

        if(nationalCode == null )
            throw new IdInputException();

        String tokenNationalCode =((UserEntity) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getNationalCode();
        if (!nationalCode.equals(tokenNationalCode))
            throw new AccessDeniedException("You do not have the required access");


        return expertiseRepository.addPhysicianExpertise(nationalCode,expertise);
    }

    @Override
    public Expertise addExpertise(Expertise expertise) {
        return expertiseRepository.addExpertise(expertise);
    }

    @Override
    public Expertise editExpertise(Long expertiseId, Expertise expertise) {
        return expertiseRepository.editExpertise(expertiseId,expertise);
    }

    @Override
    public List<Expertise> getExpertises() {
        return expertiseRepository.getExpertises();
    }

    @Override
    public List<TopExpertises> findBestExpertisePhysicians() {
        return expertiseRepository.findBestExpertisePhysicians();
    }
}
