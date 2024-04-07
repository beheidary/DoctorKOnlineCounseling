package com.doctork.doctorkonlinecounseling.boundary.in.doctor;

import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;

import java.util.List;
import java.util.UUID;

public interface DoctorService {

    Doctor addDoctor(Doctor doctor);

    Doctor editDoctor(Doctor doctor);


    List<Doctor> getDoctorsForSync();


    Doctor fetchDoctor(String pSCode);

    Expertise addDoctorExpertise(String pSCode , Expertise expertise);
}
