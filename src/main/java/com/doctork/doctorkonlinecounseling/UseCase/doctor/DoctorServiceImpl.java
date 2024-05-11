package com.doctork.doctorkonlinecounseling.UseCase.doctor;

import com.doctork.doctorkonlinecounseling.UseCase.miscellaneous.DoctorMiscellaneousServiceImpl;
import com.doctork.doctorkonlinecounseling.api.adapters.Elastic.ElasticAdapter;
import com.doctork.doctorkonlinecounseling.boundary.exit.doctor.DoctorRepository;
import com.doctork.doctorkonlinecounseling.boundary.in.doctor.DoctorService;
import com.doctork.doctorkonlinecounseling.database.entities.doctor.DoctorMongoEntity;
import com.doctork.doctorkonlinecounseling.database.jpaRepositories.DoctorMySqlRepository;
import com.doctork.doctorkonlinecounseling.database.mongoRepositories.DoctorMongoRepository;
import com.doctork.doctorkonlinecounseling.domain.SpecificModels.TopExpertises;
import com.doctork.doctorkonlinecounseling.domain.doctor.Doctor;
import com.doctork.doctorkonlinecounseling.domain.Enums.DoctorStatus;
import com.doctork.doctorkonlinecounseling.domain.doctor.Expertise;
import org.springframework.stereotype.Service;

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
    public Doctor doctorCompleteProfile(Doctor doctor) {

        //doctor.setRegisterDateTime(LocalDateTime.now());
        doctor = doctorRepository.doctorCompleteProfile(doctor);
        //elasticAdapter.doctorCompleteProfile(doctor);


        return doctor;

    }

    @Override
    public Doctor doctorEditProfile(Doctor doctor) {

        //doctor.setUpdateDateTime(LocalDateTime.now());
        doctor = doctorRepository.doctorEditProfile(doctor);
        //elasticAdapter.doctorCompleteProfile(doctor);
        return doctor;

    }

    @Override
    public List<Doctor> topDoctors() {
        return doctorRepository.topDoctors();
    }

    @Override
    public Doctor fetchDoctor(String pSCode) {

        return doctorRepository.fetchDoctor(pSCode);

    }

    @Override
    public List<TopExpertises> findBestDoctorByExpertise() {
        return doctorRepository.findBestDoctorByExpertise();
    }

    @Override
    public Expertise addDoctorExpertise(String pSCode, Expertise expertise) {
        return doctorRepository.addDoctorExpertise(pSCode,expertise);
    }

    @Override
    public Doctor changeStatus(String PSCode, DoctorStatus status) {

        //elasticAdapter.doctorCompleteProfile(doctor);

        return doctorRepository.changeStatus(PSCode,status);


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


}
