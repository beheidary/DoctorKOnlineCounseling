package com.doctork.doctorkonlinecounseling.boundary.exit.patient;

import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;

public interface PatientRepository{

    Patient patientCompleteProfile (Patient patient);

    Patient findPatientById (Long nationalCode);

    Patient fetchPatient (UserEntity userEntity);

}
