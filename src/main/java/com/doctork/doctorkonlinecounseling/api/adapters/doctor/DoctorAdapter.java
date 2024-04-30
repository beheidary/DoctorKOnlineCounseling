package com.doctork.doctorkonlinecounseling.api.adapters.doctor;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.DoctorStatus;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DoctorAdapter {


    DoctorOutputDTO addDoctor(DoctorInputDTO doctorInputDTO);

    DoctorOutputDTO editDoctor(DoctorInputDTO doctorInputDTO);
    List<TopExpertisesDto> findBestDoctorByExpertise();

    List<DoctorOutputDTO> topDoctors();


    DoctorOutputDTO fetchDoctor(String pSCode);


    ExpertiseOutputDTO addDoctorExpertise (String PSCode, ExpertiseInputDTO expertiseInputDTO);

    DoctorOutputDTO changeStatus (String PSCode , DoctorStatus status);






}
