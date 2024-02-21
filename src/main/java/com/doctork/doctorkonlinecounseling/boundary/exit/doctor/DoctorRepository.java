package com.doctork.doctorkonlinecounseling.boundary.exit.doctor;

import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.stereotype.Component;

import java.util.List;
public interface DoctorRepository {

    Doctor addDoctor(Doctor doctor);

    List<Doctor> getDoctorsForSync ();



}
