package com.doctork.doctorkonlinecounseling.api.adapters.doctor;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.api.mappers.Doctor.DoctorMapper;
import com.doctork.doctorkonlinecounseling.api.mappers.Expertise.ExpertiseMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DoctorAdapterImpl implements DoctorAdapter {


    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    private final ExpertiseMapper expertiseMapper;

    public DoctorAdapterImpl(ExpertiseMapper expertiseMapper, DoctorMapper doctorMapper, DoctorService doctorService) {
        this.doctorMapper = doctorMapper;
        this.expertiseMapper = expertiseMapper;
        this.doctorService = doctorService;
    }


    @Override
    public DoctorOutputDTO addDoctor(DoctorInputDTO doctorInputDTO) {

        Doctor doctor = doctorMapper.inputToModel(doctorInputDTO);

        doctor = doctorService.addDoctor(doctor );

        return doctorMapper.modelToOutput(doctor);

    }

    @Override
    public DoctorOutputDTO editDoctor(DoctorInputDTO doctorInputDTO) {

        Doctor doctor = doctorMapper.inputToModel(doctorInputDTO);

        doctor = doctorService.editDoctor(doctor );

        return doctorMapper.modelToOutput(doctor);

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


}
