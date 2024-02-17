package com.doctork.doctorkonlinecounseling.api.adapters.doctor;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.mappers.Doctor.DoctorMapper;
import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DoctorAdapterImpl implements DoctorAdapter {


    private final DoctorService doctorService;
    private final DoctorMapper doctorMapper;

    public DoctorAdapterImpl(DoctorMapper doctorMapper, DoctorService doctorService) {
        this.doctorMapper = doctorMapper;
        this.doctorService = doctorService;
    }


    @Override
    public DoctorOutputDTO addDoctor(DoctorInputDTO doctorInputDTO, UUID userId) {

        Doctor doctor = doctorMapper.inputToModel(doctorInputDTO);

        doctor = doctorService.addDoctor(userId, doctor );

        return doctorMapper.modelToOutput(doctor);

    }



}
