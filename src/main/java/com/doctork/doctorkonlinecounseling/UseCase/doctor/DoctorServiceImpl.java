package com.doctork.doctorkonlinecounseling.UseCase.doctor;

import com.doctork.doctorkonlinecounseling.boundary.exit.doctor.DoctorRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;
@Component
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;



    public DoctorServiceImpl(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    @Override
    public Doctor addDoctor(UUID userId, Doctor doctor) {

        doctor.setSaveDateTime (LocalDateTime.now());
        doctor = doctorRepository.addDoctor(doctor);

        return doctor;

    }
}
