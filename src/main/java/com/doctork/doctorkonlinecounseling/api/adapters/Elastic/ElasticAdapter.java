package com.doctork.doctorkonlinecounseling.api.adapters.Elastic;

import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SearchResultDTO;
import com.doctork.doctorkonlinecounseling.api.dtos.outputDTOs.miscellaneous.SuggestOutputDTO;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.entities.searchEngine.ElasticDoctorEntity;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;

import java.io.IOException;

public interface ElasticAdapter {

    Doctor setDoctorForSync(DoctorMongoEntity doctor);

    ElasticDoctorEntity deleteDoctor (String id );

    SearchResultDTO search(String queryString) throws IOException;

    SuggestOutputDTO TermSuggest(String queryString) throws IOException;

    Doctor addDoctor(Doctor doctor);

    ElasticDoctorEntity editDoctor(String id , ElasticDoctorEntity doctor);

}
