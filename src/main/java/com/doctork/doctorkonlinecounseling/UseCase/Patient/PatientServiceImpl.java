package com.doctork.doctorkonlinecounseling.UseCase.Patient;

import com.doctork.doctorkonlinecounseling.boundary.exit.patient.PatientRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.patient.PatientService;
import com.doctork.doctorkonlinecounseling.common.exceptions.input.IdInputException;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;
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

    @Override
    public Patient fetchPatient(Long nationalCode) {
        if(nationalCode == null)
            throw new IdInputException();
        return patientRepository.findPatientById(nationalCode);
    }
}
