package com.doctork.doctorkonlinecounseling.UseCase.doctor;

import com.doctork.doctorkonlinecounseling.UseCase.miscellaneous.DoctorMiscellaneousServiceImpl;
import com.doctork.doctorkonlinecounseling.api.adapters.Elastic.ElasticAdapter;
import com.doctork.doctorkonlinecounseling.boundary.exit.doctor.DoctorRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.DoctorMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.DoctorMongoRepository;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    private final DoctorRepository doctorRepository;


    private final DoctorMiscellaneousServiceImpl doctorMiscellaneousService;

    private final DoctorMongoRepository doctorMongoRepository;


    private final ElasticAdapter elasticAdapter;


    public DoctorServiceImpl(ElasticAdapter elasticAdapter, DoctorMiscellaneousServiceImpl doctorMiscellaneousService,DoctorMySqlRepository doctorMySqlRepository, DoctorRepository doctorRepository,DoctorMongoRepository doctorMongoRepository) {
        this.doctorRepository = doctorRepository;
        this.doctorMiscellaneousService = doctorMiscellaneousService;
        this.doctorMongoRepository = doctorMongoRepository;
        this.elasticAdapter = elasticAdapter;
    }

    @Override
    public Doctor addDoctor(Doctor doctor) {

        doctor.setRegisterDateTime(LocalDateTime.now());
        doctor = doctorRepository.addDoctor(doctor);
        elasticAdapter.addDoctor(doctor);


        return doctor;

    }

    @Override
    public List<Doctor> getDoctorsForSync() {


        List<DoctorMongoEntity> doctors = doctorMiscellaneousService.extractDoctors();

        List<Doctor> domaindoctors = new ArrayList<Doctor>();


        for (DoctorMongoEntity doctor : doctors) {


            try {
                domaindoctors.add(elasticAdapter.setDoctorForSync(doctor));
                doctorMongoRepository.save(doctor);
                    }catch (Exception e){
                        System.out.println(e);
                    }

        }
        return domaindoctors;

    }

    @Override
    public Doctor fetchDoctor(String pSCode) {

        return doctorRepository.fetchDoctor(pSCode);

    }

    @Override
    public Expertise addDoctorExpertise(String pSCode, Expertise expertise) {
        return doctorRepository.addDoctorExpertise(pSCode,expertise);
    }


}
