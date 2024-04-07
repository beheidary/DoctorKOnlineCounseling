package com.doctork.doctorkonlinecounseling.api.adapters.doctor;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;

import java.util.UUID;

public interface DoctorAdapter {


    DoctorOutputDTO addDoctor(DoctorInputDTO doctorInputDTO);

    DoctorOutputDTO editDoctor(DoctorInputDTO doctorInputDTO);


    DoctorOutputDTO fetchDoctor(String pSCode);


    ExpertiseOutputDTO addDoctorExpertise (String PSCode, ExpertiseInputDTO expertiseInputDTO);






}
