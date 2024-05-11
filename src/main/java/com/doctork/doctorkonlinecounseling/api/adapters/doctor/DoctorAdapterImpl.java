package com.doctork.doctorkonlinecounseling.api.adapters.doctor;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.api.mappers.DoctorMapper;
import com.doctork.doctorkonlinecounseling.api.mappers.ExpertiseMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.Enums.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class  DoctorAdapterImpl implements DoctorAdapter {


    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;


    private final ExpertiseMapper expertiseMapper;

    public DoctorAdapterImpl(ExpertiseMapper expertiseMapper, DoctorMapper doctorMapper, DoctorService doctorService) {
        this.doctorMapper = doctorMapper;
        this.expertiseMapper = expertiseMapper;
        this.doctorService = doctorService;
    }


    @Override
    public DoctorOutputDTO doctorCompleteProfile(DoctorInputDTO doctorInputDTO) {

        Doctor doctor = doctorMapper.inputToModel(doctorInputDTO);

        doctor = doctorService.doctorCompleteProfile(doctor );

        return doctorMapper.modelToOutput(doctor);

    }

    @Override
    public DoctorOutputDTO doctorEditProfile(DoctorInputDTO doctorInputDTO) {

        Doctor doctor = doctorMapper.inputToModel(doctorInputDTO);

        doctor = doctorService.doctorEditProfile(doctor );

        return doctorMapper.modelToOutput(doctor);

    }

    @Override
    public List<TopExpertisesDto> findBestDoctorByExpertise() {

        List<TopExpertisesDto> expertise = new ArrayList<>();
        for(TopExpertises topExpertises : doctorService.findBestDoctorByExpertise()){
            List<DoctorOutputDTO> doctors = new ArrayList<>();
            for (Doctor doctor : topExpertises.getDoctors()){
                doctors.add(doctorMapper.modelToOutput(doctor));
            }
            TopExpertisesDto topExpertisesDto = expertiseMapper.topModelToOutput(topExpertises);
            topExpertisesDto.setDoctors(doctors);
            expertise.add(topExpertisesDto);
        }

        return expertise;
    }

    @Override
    public List<DoctorOutputDTO> topDoctors() {
        List<DoctorOutputDTO> doctorOutputDTOS = new ArrayList<>();
        for(Doctor doctor : doctorService.topDoctors()){
            doctorOutputDTOS.add(doctorMapper.modelToOutput(doctor));
        }
        return doctorOutputDTOS;
    }

    @Override
    public DoctorOutputDTO fetchDoctor(String pSCode) {


        return doctorMapper.modelToOutput(doctorService.fetchDoctor(pSCode));
    }

    @Override
    public ExpertiseOutputDTO addDoctorExpertise(String PSCode, ExpertiseInputDTO expertiseInputDTO) {
        Expertise expertise = expertiseMapper.inputToModelWithoutDoctor(expertiseInputDTO);
        expertise = doctorService.addDoctorExpertise(PSCode , expertise);
        return expertiseMapper.modelToOutput(expertise);
    }

    @Override
    public DoctorOutputDTO changeStatus(String PSCode, DoctorStatus status) {

        return doctorMapper.modelToOutput(doctorService.changeStatus(PSCode,status));


    }


}
