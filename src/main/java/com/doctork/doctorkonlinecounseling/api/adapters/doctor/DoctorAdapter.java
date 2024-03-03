package com.doctork.doctorkonlinecounseling.api.adapters.doctor;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;

import java.util.UUID;

public interface DoctorAdapter {


    DoctorOutputDTO addDoctor(DoctorInputDTO doctorInputDTO, UUID userId);



}
