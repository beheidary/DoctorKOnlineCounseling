package com.doctork.doctorkonlinecounseling.boundary.exit.doctor;

import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.Enums.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;

import java.util.List;

public interface DoctorRepository {

    Doctor doctorCompleteProfile(Doctor doctor);

    Doctor doctorEditProfile(Doctor doctor);

    List<TopExpertises> findBestDoctorByExpertise();

    List<Doctor> topDoctors();


    Doctor fetchDoctor (String PSCode);

    Expertise addDoctorExpertise(String PSCode , Expertise expertise);

    Doctor changeStatus (String PSCode , DoctorStatus status);


}
