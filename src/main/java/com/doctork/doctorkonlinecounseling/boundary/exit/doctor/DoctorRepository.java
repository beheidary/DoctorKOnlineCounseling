package com.doctork.doctorkonlinecounseling.boundary.exit.doctor;

import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.springframework.stereotype.Component;

import java.util.List;
public interface DoctorRepository {

    Doctor addDoctor(Doctor doctor);

    List<Doctor> getDoctorsForSync ();


    Doctor fetchDoctor (String PSCode);

    Expertise addDoctorExpertise(String PSCode , Expertise expertise);


}
