package com.doctork.doctorkonlinecounseling.UseCase.doctor;

import com.doctork.doctorkonlinecounseling.UseCase.miscellaneous.DoctorMiscellaneousServiceImpl;
import com.doctork.doctorkonlinecounseling.boundary.exit.doctor.DoctorRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.boundary.in.searchEngine.ElasticService;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.DoctorMongoRepository;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;
    private final DoctorMiscellaneousServiceImpl doctorMiscellaneousService;

    private final DoctorMongoRepository doctorMongoRepository;

    private final ElasticService elasticService;


    public DoctorServiceImpl(DoctorMiscellaneousServiceImpl doctorMiscellaneousService, ElasticService elasticService, DoctorRepository doctorRepository,DoctorMongoRepository doctorMongoRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorMiscellaneousService = doctorMiscellaneousService;
        this.doctorMongoRepository = doctorMongoRepository;
        this.elasticService = elasticService;

    }

    @Override
    public Doctor addDoctor(UUID userId, Doctor doctor) {

        doctor.setSaveDateTime (LocalDateTime.now());
        doctor = doctorRepository.addDoctor(doctor);

        return doctor;

    }

    @Override
    public List<Doctor> getDoctorsForSync() {


        List<DoctorMongoEntity> doctors = doctorMiscellaneousService.extractDoctors();

        List<Doctor> domaindoctors = new ArrayList<Doctor>();


        for (DoctorMongoEntity doctor : doctors) {


            try {
                domaindoctors.add(elasticService.setDoctorForSync(doctor));
                doctorMongoRepository.save(doctor);
                    }catch (Exception e){
                        System.out.println(e);
                    }

        }
        return domaindoctors;

    }


}
