package com.doctork.doctorkonlinecounseling.boundary.exit.patient;

import com.doctork.doctorkonlinecounseling.domain.user.Patient;

public interface PatientRepository{

    Patient patientCompleteProfile (Patient patient);

}
