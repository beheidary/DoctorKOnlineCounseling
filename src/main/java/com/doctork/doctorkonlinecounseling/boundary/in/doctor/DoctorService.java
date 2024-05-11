package com.doctork.doctorkonlinecounseling.boundary.in.doctor;

import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.Enums.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;

import java.util.List;

public interface DoctorService {

    Doctor doctorCompleteProfile(Doctor doctor);

    Doctor doctorEditProfile(Doctor doctor);

    List<Doctor> topDoctors();

    List<Doctor> getDoctorsForSync();

    Doctor fetchDoctor(String pSCode);

    List<TopExpertises> findBestDoctorByExpertise();


    Expertise addDoctorExpertise(String pSCode , Expertise expertise);

    Doctor changeStatus (String PSCode , DoctorStatus status);
}
