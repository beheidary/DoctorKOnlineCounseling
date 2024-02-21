package com.doctork.doctorkonlinecounseling.boundary.in.doctor;

import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;

import java.util.List;
import java.util.UUID;

public interface DoctorService {

    Doctor addDoctor(UUID userId, Doctor doctor);

    List<Doctor> getDoctorsForSync();



}
