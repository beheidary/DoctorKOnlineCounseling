package com.doctork.doctorkonlinecounseling.api.adapters.doctor;

import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.DoctorInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.inputDTOs.doctor.ExpertiseInputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.SpecificResultDtos.TopExpertisesDto;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.DoctorOutputDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.doctor.ExpertiseOutputDTO;
import com.doctork.doctorkonlinecounseling.domain.doctor.DoctorStatus;

import java.util.List;

public interface DoctorAdapter {


    DoctorOutputDTO doctorCompleteProfile(DoctorInputDTO doctorInputDTO);

    DoctorOutputDTO doctorEditProfile(DoctorInputDTO doctorInputDTO);
    List<TopExpertisesDto> findBestDoctorByExpertise();

    List<DoctorOutputDTO> topDoctors();


    DoctorOutputDTO fetchDoctor(String pSCode);


    ExpertiseOutputDTO addDoctorExpertise (String PSCode, ExpertiseInputDTO expertiseInputDTO);

    DoctorOutputDTO changeStatus (String PSCode , DoctorStatus status);






}
