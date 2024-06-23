package com.doctork.doctorkonlinecounseling.boundary.in.patient;

import com.doctork.doctorkonlinecounseling.database.entities.user.UserEntity;
import com.doctork.doctorkonlinecounseling.domain.Patient.Patient;

public interface PatientService {

    Patient patientCompleteProfile (Patient patient);

    Patient fetchPatient (Long nationalCode);

    Patient fetchPatient (UserEntity userEntity);


}
