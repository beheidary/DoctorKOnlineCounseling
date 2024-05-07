package com.doctork.doctorkonlinecounseling.UseCase.Patient;

import com.doctork.doctorkonlinecounseling.boundary.exit.patient.PatientRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.patient.PatientService;
import com.doctork.doctorkonlinecounseling.domain.user.Patient;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {


    private final PatientRepository patientRepository;


    public PatientServiceImpl(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }
    @Override
    public Patient patientCompleteProfile(Patient patient) {

        return patientRepository.patientCompleteProfile(patient);
    }
}
