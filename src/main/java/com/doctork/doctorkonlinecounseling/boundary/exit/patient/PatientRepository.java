package com.doctork.doctorkonlinecounseling.boundary.exit.patient;

import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;

public interface PatientRepository{

    Patient patientCompleteProfile (Patient patient);

}
