package com.doctork.doctorkonlinecounseling.boundary.in.doctor;

import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);

    Doctor editDoctor(Doctor doctor);

    List<Doctor> topDoctors();

    List<Doctor> getDoctorsForSync();

    Doctor fetchDoctor(String pSCode);

    List<TopExpertises> findBestDoctorByExpertise();


    Expertise addDoctorExpertise(String pSCode , Expertise expertise);

    Doctor changeStatus (String PSCode , DoctorStatus status);
}
